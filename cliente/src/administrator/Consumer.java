package administrator;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import configuration.Configuration;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import message.ResponseMessage;

/**
 *
 * @author emanuel
 */
public class Consumer implements Runnable {

    private final String SERVER_HOST = Configuration.getInstance().getProperty(Configuration.SERVER_HOST);
    private final String SERVER_PORT = Configuration.getInstance().getProperty(Configuration.SERVER_PORT);
    private final String CONSUMER_QUEUE = Configuration.getInstance().getProperty(Configuration.CONSUMER_QUEUE);
    private final String CONSUMER_VIRTUALHOST = Configuration.getInstance().getProperty(Configuration.CONSUMER_VIRTUALHOST);

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private final Gson gson;
    private Thread thread;
    private QueueManagement management;
    

    public Consumer() {
        this.connect();
        this.gson = new Gson();
        this.management = QueueManagement.getInstance();
    }
    
    /**
     * Inicia la conexion a Rabbitmq de donde consumir mensajes.
     * 
     */
    private void connect() {
        try {
            factory = new ConnectionFactory();
            factory.setHost(SERVER_HOST);
            factory.setPort(Integer.parseInt(SERVER_PORT));
            factory.setVirtualHost(CONSUMER_VIRTUALHOST);
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(CONSUMER_QUEUE, true, false, false, null);

            System.out.println("Conexion a " + CONSUMER_QUEUE + " establecida");
        } catch (IOException | TimeoutException ex) {
            System.err.println("Error al abrir la conexion en logger sender: " + ex.getMessage());
        }
    }
    
    /**
     * Inicia la ejecucion del hilo
     * 
     */
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            sleep();
            consume();
        }
    }

    /**
     * Duerme al hilo por un tiempo determinado.
     */
    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.err.println("Excepcion " + ex.getMessage());
        }
    }
    
    /**
     * Consume los mensajes dejados por el servidor para el cliente.
     * 
     */
    private void consume() {

        try {
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(CONSUMER_QUEUE, true, consumer);

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String messageJson = new String(delivery.getBody());
                ResponseMessage responseMessage = gson.fromJson(messageJson, ResponseMessage.class);

                System.out.println(" [x] Received '" + responseMessage.toString());
            }

        } catch (IOException | InterruptedException | ShutdownSignalException | ConsumerCancelledException | JsonSyntaxException ex) {
            System.err.println("Error al consumir mensaje: " + ex.getMessage());
        }
    }

}

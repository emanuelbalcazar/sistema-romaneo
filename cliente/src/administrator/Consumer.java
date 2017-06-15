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
import logger.Logger;
import logger.Status;
import message.Message;
import message.ResponseMessage;
import message.TextMessage;

/**
 *
 * @author emanuel
 */
public class Consumer implements Runnable {

    private final String SERVER_HOST = Configuration.getInstance().getProperty(Configuration.SERVER_HOST);
    private final String SERVER_PORT = Configuration.getInstance().getProperty(Configuration.SERVER_PORT);
    //private final String CONSUMER_QUEUE = Configuration.getInstance().getProperty(Configuration.CONSUMER_QUEUE);
    private final String CONSUMER_VIRTUALHOST = Configuration.getInstance().getProperty(Configuration.CONSUMER_VIRTUALHOST);
    private String QUEUE_NAME;

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private final Gson gson;
    private Thread thread;
    private QueueManagement management;
    private final int imei;

    public Consumer(int i) {
        this.gson = new Gson();
        this.management = QueueManagement.getInstance();
        this.imei = i;
    }

    /**
     * Inicia la conexion a Rabbitmq de donde consumir mensajes.
     *
     */
    private void connect() {
        final String EXCHANGE_NAME = "messages";
        System.out.println(">>> " + this.imei);
        try {
            factory = new ConnectionFactory();
            factory.setHost(SERVER_HOST);
            factory.setPort(Integer.parseInt(SERVER_PORT));
            factory.setVirtualHost(CONSUMER_VIRTUALHOST);
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            QUEUE_NAME = channel.queueDeclare().getQueue();

            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, Integer.toString(imei));

            System.out.println("Conexion a " + QUEUE_NAME + " establecida con Key: " + imei);
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
            this.connect();
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
            channel.basicConsume(QUEUE_NAME, true, consumer); // true - remueve el mensaje una vez consumido de la cola.

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String messageJson = new String(delivery.getBody());
                ResponseMessage responseMessage = gson.fromJson(messageJson, ResponseMessage.class);

                System.out.println(" [X] Received " + responseMessage.toString());
                Message res = adapteResponseMessage(responseMessage);
                Logger.getInstance().logTrace(res, messageJson, messageJson, messageJson);
            }

        } catch (IOException | InterruptedException | ShutdownSignalException | ConsumerCancelledException | JsonSyntaxException ex) {
            System.err.println("Error al consumir mensaje: " + ex.getMessage());
        }
    }

    private Message adapteResponseMessage(ResponseMessage response) {
        Message result = new TextMessage();
        
        result.setId(response.getMessageId());
        result.setImei(response.getImei());
        result.setType(response.getMessageType());
        result.setSubType(response.getMessageSubType());
        result.setDescription(response.getDescription());

        return result;
    }
}

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
import validators.Parser;

/**
 * Clase encargada de consumir de la cola de Rabbit los mensajes correspondientes
 * al dispositivo movil en ejecucion.
 * 
 */
public class Consumer implements Runnable {

    private final String SERVER_HOST = Configuration.getInstance().getProperty(Configuration.SERVER_HOST);
    private final String SERVER_PORT = Configuration.getInstance().getProperty(Configuration.SERVER_PORT);
    private final String CONSUMER_VIRTUALHOST = Configuration.getInstance().getProperty(Configuration.CONSUMER_VIRTUALHOST);
    private String EXCHANGE_NAME = Configuration.getInstance().getProperty(Configuration.EXCHANGE);

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private final Gson gson;
    private Thread thread;
    private QueueManagement management;
    private final int imei;
    private String queueName;
    private Parser parser;
    
    public Consumer(int i) {
        this.gson = new Gson();
        this.management = QueueManagement.getInstance();
        this.imei = i;
        this.parser = new Parser();
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
            factory.setPassword("admin");
            factory.setUsername("admin");
            connection = factory.newConnection();
            channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, Integer.toString(imei));

            System.out.println("Conexion a " + queueName + " establecida con Key: " + imei);
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
     * Los mensajes se consumen de la cola indicada por el Exchange acorde a
     * la clave recibida como argumento durante la conexion.
     */
    private void consume() {

        try {
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(queueName, false, consumer); // true - remueve el mensaje una vez consumido de la cola.

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String messageJson = new String(delivery.getBody());
                ResponseMessage responseMessage = gson.fromJson(messageJson, ResponseMessage.class);

                System.out.println(" [X] Received " + responseMessage.toString());
                Message res = adapteResponseMessage(responseMessage);
                System.out.println("RES " + res.toString());
                Logger.getInstance().logTrace(res, "cliente", Status.RECEIVED.getStatus(), "Mensaje recibido de por parte del servidor.");
               // parser.parseMessage(responseMessage);
            }

        } catch (IOException | InterruptedException | ShutdownSignalException | ConsumerCancelledException | JsonSyntaxException ex) {
            System.err.println("Error al consumir mensaje: " + ex.getMessage());
        }
    }
    
    /**
     * Adapta un mensaje de tipo ResponseMessage.java a un mensaje valido para el Logger.
     * 
     * @param response mensaje de respuesta del servidor.
     * @return un mensaje que extiende de Message.java valido para ser 
     * utilizado por el Logger.
     */
    private Message adapteResponseMessage(ResponseMessage response) {
        Message result = new TextMessage();  // Porque de texto? no se, pero es util.
        
        result.setId(response.getMessageId());
        result.setImei(response.getImei());
        result.setType(response.getMessageType());
        result.setSubType(response.getMessageSubType());
        result.setDescription(response.getDescription());

        return result;
    }
}

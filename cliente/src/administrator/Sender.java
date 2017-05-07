package administrator;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import configuration.Configuration;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import message.Message;

/**
 * Clase encargada de enviar los mensajes recibidos a la cola de RabbitMQ.
 *
 */
public class Sender {
    
    // valores de configuracion extraidos del archivo properties.
    private final String SERVER_QUEUE = Configuration.getInstance().getProperty(Configuration.SERVER_QUEUE);
    private final String VIRTUAL_HOST = Configuration.getInstance().getProperty(Configuration.SERVER_VIRTUALHOST);
    private final String SERVER_HOST = Configuration.getInstance().getProperty(Configuration.SERVER_HOST);

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private boolean connected;

    public Sender() {
        this.connected = false;
    }
    
    /**
     * Inicia la conexion y crea un canal de comunicacion a RabbitMQ.
     * Se setean los parametros de conexion configurados en el archivo properties.
     */
    public void connect() {
        try {
            factory = new ConnectionFactory();
            factory.setHost(SERVER_HOST);
            factory.setVirtualHost(VIRTUAL_HOST);
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(SERVER_QUEUE, true, false, false, null);
            connected = true;
        } catch (IOException | TimeoutException ex) {
            System.err.println("Error al abrir la conexion: " + ex.getMessage());
        }
    }
    
    /**
     * Envia un mensaje a la cola de RabbitMQ.
     * @param msg mensaje a enviar, dicho mensaje es convertido a JSON.
     */
    public boolean sendMessage(Message msg) {

        try {
            Gson gson = new Gson();
            String messageJson = gson.toJson(msg);
            channel.basicPublish("", SERVER_QUEUE, null, messageJson.getBytes());
        } catch (IOException ex) {
            System.err.println("Error al enviar el mensaje: " + ex.getMessage());
        }
        return true;
    }
    
    /**
     * Retorna un booleano indicando si la conexion a RabbitMQ esta iniciada.
     * @return boolean
     */
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * Cierra la conexion a RabbitMQ.
     */
    public void close() {
        try {
            channel.close();
            connection.close();
            connected = false;
        } catch (IOException | TimeoutException ex) {
            System.err.println("Error al cerrar la conexion: " + ex.getMessage());
        }
    }

}

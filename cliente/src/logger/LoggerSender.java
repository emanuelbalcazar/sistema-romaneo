package logger;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import configuration.Configuration;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Clase encargada de enviar a la cola de Logger los mensajes de logs generados.
 *
 */
public class LoggerSender {

    private final String LOGGER_QUEUE = Configuration.getInstance().getProperty(Configuration.LOGGER_QUEUE);
    private final String LOGGER_HOST = Configuration.getInstance().getProperty(Configuration.LOGGER_HOST);
    private final String LOGGER_VIRTUALHOST = Configuration.getInstance().getProperty(Configuration.LOGGER_VIRTUALHOST);
    private final String LOGGER_PORT = Configuration.getInstance().getProperty(Configuration.LOGGER_PORT);

    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    private LoggerSender() {
        this.connect();
    }

    /**
     * Inicia la conexion a Rabbitmq.
     */
    private void connect() {
        try {
            factory = new ConnectionFactory();
            factory.setHost(LOGGER_HOST);
            factory.setPort(Integer.parseInt(LOGGER_PORT));
            factory.setVirtualHost(LOGGER_VIRTUALHOST);
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(LOGGER_QUEUE, true, false, false, null);
        } catch (IOException | TimeoutException ex) {
            System.err.println("Error al abrir la conexion en logger sender: " + ex.getMessage());
        }
    }

    /**
     * Envia un mensaje de log
     *
     * @param log mensaje de log a encolar en rabbitmq.
     */
    public void sendLog(LogMessage log) {
        try {
            Gson gson = new Gson();
            String messageJson = gson.toJson(log);
            channel.basicPublish("", LOGGER_QUEUE, null, messageJson.getBytes());
        } catch (IOException ex) {
            System.err.println("Error al enviar el mensaje de log " + ex.getMessage());
        }

    }

    public static LoggerSender getInstance() {
        return LoggerSenderHolder.INSTANCE;
    }

    private static class LoggerSenderHolder {
        private static final LoggerSender INSTANCE = new LoggerSender();
    }
}

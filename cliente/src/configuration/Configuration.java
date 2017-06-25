package configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase encargada de obtener los valores configurados en un archivo de
 * properties. Util si se desea agrupar la configuracion fuera de una clase
 * Java.
 *
 */
public class Configuration {

    private Properties properties;
    private final String CONFIG_FILE_NAME = "./config.properties";

    // Constantes estaticas utilizadas para acceder a los valores en el archivo properties.
    public static final String SERVER_QUEUE = "Server.queue";
    public static final String SERVER_HOST = "Server.host";
    public static final String SERVER_VIRTUALHOST = "Server.virtualhost";
    public static final String SERVER_PORT = "Server.port";

    public static final String LOGGER_QUEUE = "Logger.queue";
    public static final String LOGGER_HOST = "Logger.host";
    public static final String LOGGER_VIRTUALHOST = "Logger.virtualhost";
    public static final String LOGGER_PORT = "Logger.port";

    public static final String MESSAGE_PROB_ERROR = "Message.probError";

    public static final String ROMANEO_SLEEP = "RomaneoGenerator.sleep";
    public static final String TEXT_SLEEP = "TextGenerator.sleep";
    public static final String GEO_SLEEP = "GeolocationGenerator.sleep";

    public static final String CONSUMER_VIRTUALHOST = "Consumer.virtualhost";
    public static final String EXCHANGE = "Consumer.exchange";
    
    public static final String GEO_RANDOM_MOVE_PHONE = "GeolocationGenerator.randomMovePhone";
    public static final String GEO_MOVE_PHONE = "GeolocationGenerator.movePhone";
    public static final String GEO_MIN_LAT = "GeolocationGenerator.minLat";
    public static final String GEO_MAX_LAT = "GeolocationGenerator.maxLat";
    public static final String GEO_DISTANCE = "GeolocationGenerator.distance";
    
    public static final String DEVICES_CANT = "Customer.mobiles";
    public static final String CONNECTIVITY_PROB = "Connectivity.prob";
    

    private Configuration() {
        this.properties = new Properties();
        readPropertiesFile();
    }

    /**
     * Lee el archivo de properties y lo inicializa en el atributo privado de la
     * clase.
     */
    private void readPropertiesFile() {
        try {
            try (FileInputStream in = new FileInputStream(CONFIG_FILE_NAME)) {
                properties.load(in);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    /**
     * Utilizado para la implementacion del patron de diseño Singleton.
     *
     * @return una unica instancia de la clase.
     */
    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }

    private static class ConfigurationHolder {

        private static final Configuration INSTANCE = new Configuration();
    }
}

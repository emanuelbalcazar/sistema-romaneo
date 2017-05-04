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

    private static Properties properties;
    private static final String CONFIG_FILE_NAME = "config.properties";

    // Constantes estaticas utilizadas para acceder a los valores en el archivo properties.
    public static final String CONNECTIVITY_SLEEP_TIME = "Connectivity.SleepTime";
    public static final String CONNECTIVITY_PROBABILITY = "Connectivity.Probability";

    public Configuration() {
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

    public static String getStringProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntegerProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

}

package connectivity;

import configuration.Configuration;
import java.util.Random;

/**
 * Simulador de conectividad, simula de forma aleatoria la probabilidad
 * de poseer una conectividad ficticia en el dispositivo movil.
 * 
 */
public class ConnectivitySimulator implements Runnable {
    
    private final Random rand;
    private Thread thread;
    private boolean hasConnectivity;
    
    // Obtengo los valores configurados en el archivo de properties.
    private final int SLEEP_TIME = 5000;
    private final int PROBABILITY = 80;

    public ConnectivitySimulator() {
        this.rand = new Random();
        this.hasConnectivity = false;
    }
    
   /**
    * Inicia la ejecucion del hilo.
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
            waitRandomTime();
            determineConectivity();
        }
    }
    
    /**
     * Duerme al hilo por un determinado tiempo para simular el transcurso del
     * tiempo sin conexion
     */
    private void waitRandomTime() {
        try {
            Thread.sleep(rand.nextInt(SLEEP_TIME));
        } catch (InterruptedException ex) {
            System.err.println("Excepcion " + ex.getMessage());
        }
    }
    
    /**
     * Determina si el dispositivo posee conectividad segun la probabilidad
     * configurada de poseer conectividad.
     */
    private void determineConectivity() {
        int value = rand.nextInt(100);
        hasConnectivity = (value < PROBABILITY);
    }
    
    public boolean hasConnectivity() {
        return hasConnectivity;
    }
    
}

package connectivity;

import configuration.Configuration;
import java.util.Random;

/**
 * Simulador de connectividad, simula aleatoriamente la conectividad
 * del dispositivo.
 * 
 */
public class ConnectivitySimulator implements Runnable {
    
    private final Random rand;
    private Thread thread;
    private boolean hasConnectivity;
    private final String PROB = Configuration.getInstance().getProperty(Configuration.CONNECTIVITY_PROB);
    
    public ConnectivitySimulator() {
        this.rand = new Random();
        this.hasConnectivity = false;
    }
    
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
            Thread.sleep(rand.nextInt(5000));
        } catch (InterruptedException ex) {
            System.err.println("Excepcion " + ex.getMessage());
        }
    }
    
    
    private void determineConectivity() {
        int value = rand.nextInt(100);
        hasConnectivity = (value < Integer.parseInt(PROB));
    }
    
    public boolean hasConnectivity() {
        return hasConnectivity;
    }
    
}

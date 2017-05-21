package administrator;

import connectivity.ConnectivitySimulator;
import message.Message;
import generators.GeneratorEngine;
import generators.GeneratorEngineImple;

/**
 * Clase encargada de administrar el envio y manejo de mensajes. Es
 * intermediario entre los generadores y RabbitMQ.
 *
 */
public class Administrator implements Runnable {

    private final ConnectivitySimulator connectivitySimulator;
    private final QueueManagement queueManagement;
    private final GeneratorEngine generators;
    private final Sender sender;
    private Thread thread;
    private int imei;

    public Administrator(int imei) {
        this.connectivitySimulator = new ConnectivitySimulator();
        this.queueManagement = new QueueManagement();
        this.sender = new Sender();
        this.generators = new GeneratorEngineImple(queueManagement);
        this.imei = imei;
    }
    
    /**
     * Inicia la ejecucion del hilo.
     */
    public void start() {
        inicialize();

        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }

    }
    
    /**
     * Inicializa los simuladores correspondientes (generadores, simuladores).
     * 
     */
    private void inicialize() {
        sender.connect();
        generators.setImei(imei);
        generators.startGenerators();
        connectivitySimulator.start();
    }

    @Override
    public void run() {
        while (true) {
            waitTime();
            sendMessage();
        }
    }
    
    /**
     * Duerme al hilo por un tiempo determinado.
     */
    private void waitTime() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.err.println("Excepcion " + ex.getMessage());
        }
    }
    
    /**
     * Verifica si el dispositivo posee conectividad, en caso de ser asi toma un
     * mensaje de la cola de listos para enviar y lo manda a la cola de mensajeria 
     * utilizando los objetos correspondientes encargados de dicha tarea.
     */
    private void sendMessage() {
        if (connectivitySimulator.hasConnectivity() && queueManagement.hasMessageToSend()) {
            Message msg = queueManagement.getMessageToSend();
            boolean send = sender.sendMessage(msg);
            sentStatus(send, msg);
        } else {
            System.out.println("No hay nada para enviar");
        }
    }
    
    /**
     * Verifica si el mensaje pudo ser enviado, en caso de exito coloca el mensaje
     * enviado en la cola de mensajes enviados utilizando el manejador de colas.
     * @param send booleano indicando si el mensaje fue enviado con exito.
     * @param msg mensaje tomado de la cola de listos para enviar.
     */
    private void sentStatus(boolean send, Message msg) {
        if (send) {
            queueManagement.setMessageSent(msg);
        } else {
            System.err.println("El mensaje no pudo ser enviado");
        }
    }
}

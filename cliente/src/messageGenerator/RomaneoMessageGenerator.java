package messageGenerator;

import message.Priority;
import message.RomaneoMessage;
import message.SubTypes;
import message.Types;

/**
 * Implementacion del generador de mensajes de tipo Romaneo.
 * 
 */
public class RomaneoMessageGenerator extends MessageGeneratorGeneric {
    
    private final int SLEEP_TIME = 5000;
    private int id = 0; // TODO - remover, solo temporal.

    public RomaneoMessageGenerator() {
        
    }

    /**
     * Inicia la ejecucion del hilo.
     */
    @Override
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            waitTime();
            generateMessage();
        }
    }
    
    /**
     * Duerme al hilo por un tiempo determinado.
     */
    private void waitTime() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Genera un mensaje y se lo pasa al Manejador de Colas.
     */
    private void generateMessage() {
        RomaneoMessage msg = new RomaneoMessage();
        msg.setId(++id);
        msg.setImei(1);
        msg.setType(Types.ROMANEO.getType());
        msg.setSubtype(SubTypes.START_ROMANEO.getSubType());
        msg.setPriority(Priority.HIGH_PRIORITY.getPriority());
        msg.setEstablishment("UNPSJB");
        msg.setProducer("Carlos Emanuel Balcazar");
        management.addMessageToSend(msg);
    }

}

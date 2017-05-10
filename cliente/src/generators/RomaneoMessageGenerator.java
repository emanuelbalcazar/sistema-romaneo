package generators;

import message.Priority;
import message.RomaneoMessage;
import message.Type;

/**
 * Implementacion de un generador de mensajes de tipo Romaneo.
 * 
 */
public class RomaneoMessageGenerator extends MessageGeneratorGeneric {
    
    private int id; // TODO - remover en el proximo refactor
    private int idRomaneo;
    
    public RomaneoMessageGenerator() {
        this.id = 0;
        this.idRomaneo = 0;
    }

    /**
     * Execute thread
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

    private void waitTime() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void generateMessage() {
        RomaneoMessage msg = new RomaneoMessage();
        msg.setId(id);
        msg.setIdRomaneo(++id);
        msg.setImei(1);
        msg.setType(Type.ROMANEO.getType());
        msg.setOperation("Inicio de Romaneo");
        msg.setPriority(Priority.HIGH_PRIORITY.getPriority());
        msg.setEstablishment("UNPSJB");
        msg.setContractor("Carlos Emanuel Balcazar");
        
        management.addMessageToSend(msg);
    }

}

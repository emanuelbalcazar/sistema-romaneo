package messageGenerator;

import message.Message;
import message.RomaneoMessage;

/**
 *
 * @author emanuel
 */
public class RomaneoMessageGenerator extends MessageGeneratorGeneric {

    public RomaneoMessageGenerator() {

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
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void generateMessage() {
        RomaneoMessage msg = new RomaneoMessage();
        msg.setId(1);
        msg.setImei(1);
        msg.setType("ROMANEO");
        msg.setPriority(2);
        msg.setEstablishment("UNPSJB");
        
        management.addMessageToSend(msg);
    }

}

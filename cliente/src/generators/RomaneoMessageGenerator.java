package generators;

import configuration.Configuration;
import java.util.Random;
import logger.Logger;
import logger.Status;
import message.Priority;
import message.RomaneoMessage;
import message.SubType;
import message.Type;

/**
 * Implementacion de un generador de mensajes de tipo Romaneo.
 *
 */
public class RomaneoMessageGenerator extends MessageGeneratorGeneric {

    private int idRomaneo;
    private final Random rand;
    private int secuence;

    private final String PROB_ERROR = Configuration.getInstance().getProperty(Configuration.MESSAGE_PROB_ERROR);
    private final String SLEEP = Configuration.getInstance().getProperty(Configuration.ROMANEO_SLEEP);  // miliseconds

    // Secuencia de pasos de un Romaneo.
    private final String[] secuenceRom = {
        SubType.START_ROMANEO.getSubType(),
        SubType.BALE.getSubType(),
        SubType.QUALITY_SAMPLE.getSubType(),
        SubType.QUALITY_SAMPLE_CLOSURE.getSubType(),
        SubType.END_ROMANEO.getSubType()
    };

    public RomaneoMessageGenerator() {
        this.idRomaneo = 0;
        this.rand = new Random();
        this.secuence = 0;
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

    /**
     * Duerme al hilo por un tiempo determinado.
     */
    private void waitTime() {
        try {
            Thread.sleep(Integer.parseInt(SLEEP));
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Genera un nuevo mensaje y lo agrega a la cola de listos para enviar.
     */
    private void generateMessage() {
        RomaneoMessage msg = createMessage();
        Logger.getInstance().logInfo(msg, "cliente: " + this.imei, Status.GENERATED.getStatus(), "Se genero el mensaje de Romaneo: " + msg.getSubType());
        management.addMessageToSend(msg);
    }

    /**
     * Crea un nuevo mensaje acorde a la secuencia de pasos de romaneo y
     * dependiendo la probabilidad de que se genere un mensaje erroneo.
     *
     * @return un nuevo mensaje de romaneo.
     */
    private RomaneoMessage createMessage() {
        RomaneoMessage msg;

        if (rand.nextInt(100) < Integer.parseInt(PROB_ERROR)) {
            msg = generateMessageError();
        } else {
            msg = generateRomaneoMessageSecuence();
        }

        return msg;
    }
    
    /**
     * Genera un mensaje de tipo error para simular los mensajes erroneos
     * 
     * @return un mensaje de romaneo con error. 
     */
    private RomaneoMessage generateMessageError() {
        RomaneoMessage msg = new RomaneoMessage();

        msg.setId(++idRomaneo);  // TODO - eliminar, buscar otra forma de generar IDs.
        msg.setIdRomaneo(rand.nextInt(1000));
        msg.setImei(this.imei);
        msg.setEstablishment("ERROR");
        msg.setType(Type.ROMANEO.getType());
        msg.setSubType(SubType.ERROR.getSubType());
        msg.setPriority(Priority.HIGH_PRIORITY.getPriority());

        return msg;
    }
    
    /**
     * Genera un mensaje siguiendo la secuencia de pasos de Romaneo.
     * 
     * @return un mensaje de romaneo en secuencia.
     */
    private RomaneoMessage generateRomaneoMessageSecuence() {
        RomaneoMessage msg = new RomaneoMessage();

        msg.setId(++idRomaneo);
        msg.setIdRomaneo(rand.nextInt(1000));
        msg.setImei(this.imei);
        msg.setEstablishment("UNPSJB");
        msg.setType(Type.ROMANEO.getType());
        msg.setSubType(getSecuence());
        msg.setPriority(Priority.HIGH_PRIORITY.getPriority());
        
        secuence++;
        
        return msg;
    }

    /**
     * 
     * @return la secuencia de operacion de romaneo; 
     */
    private String getSecuence() {
        secuence = (secuence == secuenceRom.length) ? 0 : secuence;
        return secuenceRom[secuence];
    }

}

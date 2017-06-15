/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import configuration.Configuration;
import logger.Logger;
import logger.Status;
import message.Priority;
import message.TextMessage;
import message.Type;

/**
 * Implementacion de un Generador de mensajes de tipo texto comun
 *
 * @author luna
 */
public class TextMessageGenerator extends MessageGeneratorGeneric {

    private int id;
    private final String SLEEP = Configuration.getInstance().getProperty(Configuration.TEXT_SLEEP);  // miliseconds

    public TextMessageGenerator() {

        this.id = 0;
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
            Thread.sleep(Integer.parseInt(SLEEP));
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void generateMessage() {
        TextMessage msg = new TextMessage();
        msg.setId(++id);
        msg.setImei(this.imei);
        msg.setPriority(Priority.LOW_PRIORITY.getPriority());
        msg.setType(Type.TEXT.getType());
        msg.setOperation("2");
        msg.setText("Mensaje de Texto: Hola Mundo");
        Logger.getInstance().logInfo(msg, "cliente", Status.GENERATED.getStatus(), "Se genero el mensaje de Texto");

        management.addMessageToSend(msg);
    }

}

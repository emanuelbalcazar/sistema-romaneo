/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import message.Priority;
import message.TextMessage;
import message.Type;

/**
 * Implementacion de un Generador de mensajes de tipo texto comun
 * 
 * @author luna
 */
public class TextMessageGenerator extends MessageGeneratorGeneric{

    private int id;

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
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void generateMessage() {
        TextMessage msg = new TextMessage();
        msg.setId(++id);
        msg.setImei(1);
        msg.setPriority(Priority.LOW_PRIORITY.getPriority());
        msg.setType(Type.TEXT.getType());
        msg.setOperation("2");
        msg.setText("HOLA WACHEN!!!");
        management.addMessageToSend(msg);
    }
   
}
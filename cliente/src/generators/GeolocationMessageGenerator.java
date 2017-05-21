/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import configuration.Configuration;
import message.GeolocationMessage;
import message.Priority;
import message.Type;

/**
 *
 * @author nahuel
 */
public class GeolocationMessageGenerator extends MessageGeneratorGeneric {

    private int id; // TODO - remover en el proximo refactor
    private final String SLEEP = Configuration.getInstance().getProperty(Configuration.GEO_SLEEP);  // miliseconds

    public GeolocationMessageGenerator() {
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
        GeolocationMessage msg = new GeolocationMessage();
        msg.setId(++id);
        msg.setImei(1);
        msg.setType(Type.GEOLOCATION.getType());
        msg.setLatitude(-44.676868);
        msg.setLongitude(-66.786876);
        msg.setPriority(Priority.HIGH_PRIORITY.getPriority());

        management.addMessageToSend(msg);
    }

}

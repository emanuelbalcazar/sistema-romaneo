/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generators;

import configuration.Configuration;
import java.util.Random;
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
    private final String RANDOM_MOVE_PHONE = Configuration.getInstance().getProperty(Configuration.GEO_RANDOM_MOVE_PHONE);
    private final String MOVE_PHONE = Configuration.getInstance().getProperty(Configuration.GEO_MOVE_PHONE);
    private final String MIN_LAT = Configuration.getInstance().getProperty(Configuration.GEO_MIN_LAT);
    private final String MAX_LAT = Configuration.getInstance().getProperty(Configuration.GEO_MAX_LAT);
    private final String DISTANCE = Configuration.getInstance().getProperty(Configuration.GEO_DISTANCE);
    private final int randomMovePhone;
    private final int movePhone;
    private final int minLat;
    private final int maxLat;
    private final int distance;
    private double beforeLatitude;
    private double beforeLongitude;
    
    
    
    public GeolocationMessageGenerator() {
        this.id = 0;
        this.beforeLatitude=0;
        this.beforeLongitude =0;
        this.randomMovePhone = Integer.parseInt(RANDOM_MOVE_PHONE);
        this.movePhone = Integer.parseInt(MOVE_PHONE);
        this.minLat = Integer.parseInt(MIN_LAT);
        this.maxLat = Integer.parseInt(MAX_LAT);
        this.distance = Integer.parseInt(DISTANCE); 
        
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

        GeolocationMessage msg = generatorMessage();

        if (distanciaCoord(msg.getLatitude(), msg.getLongitude(), beforeLatitude, beforeLongitude) == true) {
            management.addMessageToSend(msg);
            beforeLatitude = msg.getLatitude();
            beforeLongitude = msg.getLongitude();
            System.out.println("Se envia Mensaje");
        }
        else{
            System.out.println("Se encuentra en el mismo sitio, NO se envia el mensaje");
        }

    }

    private GeolocationMessage generatorMessage() {
        GeolocationMessage msg = new GeolocationMessage();
        msg.setId(++id);
        msg.setImei(this.imei);
        msg.setType(Type.GEOLOCATION.getType());
        if(movePhone() > movePhone){
        msg.setLatitude(lantRange());
        }
        else{
            msg.setLatitude(beforeLatitude);
        }
        msg.setLongitude(-66.786876);
        msg.setPriority(Priority.HIGH_PRIORITY.getPriority());

        return msg;

    }
    
    public int movePhone(){
        Random r = new Random();
		return r.nextInt() * (randomMovePhone);
    }
    
    private float lantRange() {
		Random r = new Random();
		return minLat + r.nextFloat() * (maxLat - (minLat));
	}

    private boolean distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
        //double radioTierra = 3958.75;//en millas  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
        double distancia = radioTierra * va2;

        return distancia > distance;
    }

}

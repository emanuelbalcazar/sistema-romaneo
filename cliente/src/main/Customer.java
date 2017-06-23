/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import configuration.Configuration;
import java.util.ArrayList;
import simulator.MobileDevice;

/**
 *
 * @author emanuel
 */
public class Customer {

    public static void main(String[] args) {
        ArrayList<MobileDevice> all = new ArrayList<>();
        
        String devices = Configuration.getInstance().getProperty(Configuration.DEVICES_CANT);
        int quantity = Integer.parseInt(devices);
                
        for (int i = 0; i < quantity; i++) {
            MobileDevice mobile = new MobileDevice(i);
            all.add(mobile);
        }
        
        for (MobileDevice mobile : all) {
            mobile.start();
        }
        
        System.out.println("Cantidad de dispositivos iniciados: " + all.size());
        
    }
}

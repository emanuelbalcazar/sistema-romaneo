/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import administrator.Administrator;

/**
 *
 * @author emanuel
 */
public class MobileDevice {
    
    private int imei;
    private Administrator administrator;
    
    public MobileDevice(int imei) {
        this.imei = imei;
        this.administrator = new Administrator(imei);
    }
    
    public void start() {
        administrator.start();
    }
   
}

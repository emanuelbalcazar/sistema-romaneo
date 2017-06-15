/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import simulator.MobileDevice;

/**
 *
 * @author emanuel
 */
public class Customer {
    
    public static void main(String[] args) {
                
        MobileDevice mobile1 = new MobileDevice(1);
        mobile1.start();
        
        MobileDevice mobile2 = new MobileDevice(2);
        mobile2.start();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrator;

import com.google.gson.Gson;
import message.Message;

/**
 *
 * @author emanuel
 */
public class Sender {


    public Sender() {

    }

    
    public void sendMessage(Message msg) {
        Gson gson = new Gson();
        String jsonMessage = gson.toJson(msg);
        System.out.println("Mensaje a enviar " + jsonMessage);
    }
}

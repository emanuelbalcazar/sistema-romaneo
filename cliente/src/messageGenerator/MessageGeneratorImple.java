/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageGenerator;

import messageAdministrator.QueueManagement;


public class MessageGeneratorImple implements MessageGenerator {
    
    private final QueueManagement queueManagement;
    
    public MessageGeneratorImple(QueueManagement management) {
        this.queueManagement = management;
    }
    
    
    @Override
    public void generateMessages() {
        MessageGeneratorGeneric romaneo = new RomaneoMessageGenerator();
        romaneo.setQueueManagement(queueManagement);
        romaneo.start();
    }
    
}

package messageGenerator;

import messageAdministrator.QueueManagement;

/**
 *
 * @author emanuel
 */
public abstract class MessageGeneratorGeneric implements Runnable {
    
    protected QueueManagement management;
    protected Thread thread;

    public MessageGeneratorGeneric() {
        
    }
    
    public void setQueueManagement(QueueManagement management) {
        this.management = management;
    }
    
    public abstract void start();
    
}

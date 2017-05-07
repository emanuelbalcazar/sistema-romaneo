package generators;

import administrator.QueueManagement;

/**
 * Abstraccion generica de un generador de mensajes.
 * Todos los generadores de mensajes extienden de esta clase.
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

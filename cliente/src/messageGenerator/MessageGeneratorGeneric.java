package messageGenerator;

/**
 * Implementacion abstracta de un generador de mensajes.
 * Todos los generadores reciben una referencia a un objeto en comun donde
 * deben colocar los mensajes generados a eso se debe esta abstraccion.
 */
public abstract class MessageGeneratorGeneric implements Runnable {
    
    protected QueueManagement management;
    protected Thread thread;

    public MessageGeneratorGeneric() {
        
    }
    
    /**
     * Setea al generador una referencia al objeto encargado de administrar
     * las colas de mensajes.
     * @param management manejador de colas.
     */
    public void setQueueManagement(QueueManagement management) {
        this.management = management;
    }
    
    /**
     * Metodo abstracto a implementar por cada generador individual.
     * Este metodo inicia la ejecucion del generador de mensajes.
     */
    public abstract void start();
    
}

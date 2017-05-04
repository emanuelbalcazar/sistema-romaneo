package messageGenerator;

import java.util.PriorityQueue;
import message.Message;

/**
 * Clase encargada de administrar las colas de mensajes.
 * Las colas de mensajes son creadas y administradas por esta clase.
 */
public class QueueManagement {
    
    // Cola priorizada, los objetos se ordenan segun su orden de prioridad.
    private final PriorityQueue<Message> queueToSend;

    public QueueManagement() {
        this.queueToSend = new PriorityQueue<>();
    }
    
    /**
     * Agrega un mensaje nuevo a la cola de listos para enviar.
     * @param msg mensaje a agregar.
     */
    public void addMessageToSend(Message msg) {
        queueToSend.add(msg);
    }
    
    /**
     * @return booleano indicando si la cola esta o no vacia. 
     */
    public boolean hasMessageToSend() {
        return (!queueToSend.isEmpty());
    }
    
    /**
     * Retorna el primer mensaje disponible para ser enviado.
     * @return el primer elemento de la cola.
     */
    public Message getMessageToSend() {
        return queueToSend.poll();              
    }
    
}

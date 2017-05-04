package messageAdministrator;

import java.util.PriorityQueue;
import message.Message;

/**
 *
 * @author emanuel
 */
public class QueueManagement {
    
    private final PriorityQueue<Message> queueToSend;

    public QueueManagement() {
        this.queueToSend = new PriorityQueue<>();
    }
    
    public void addMessageToSend(Message msg) {
        queueToSend.add(msg);
    }
    
    public boolean hasMessageToSend() {
        return (!queueToSend.isEmpty());
    }
    
    public Message getMessageToSend() {
        return queueToSend.poll();              
    }
    
}

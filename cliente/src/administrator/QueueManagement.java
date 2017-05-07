package administrator;

import java.util.PriorityQueue;
import message.Message;

/**
 * Clase encargada de manejar las colas de mensajes del dispositivo. Esta clase
 * es la unica que puede mover los mensajes de una cola a otra dependiendo de
 * las confirmaciones recibidas por el servidor.
 */
public class QueueManagement {

    private final PriorityQueue<Message> queueToSend; // mensajes para enviar
    private final PriorityQueue<Message> sentQueue;  // mensajes enviados

    public QueueManagement() {
        this.queueToSend = new PriorityQueue<>();
        this.sentQueue = new PriorityQueue<>();
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

    public void setMessageSent(Message msg) {
        sentQueue.add(msg);
    }

}

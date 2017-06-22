package administrator;

import configuration.Configuration;
import java.util.Iterator;
import java.util.PriorityQueue;
import message.Message;
import message.ResponseMessage;

/**
 * Clase encargada de manejar las colas de mensajes del dispositivo. Esta clase
 * es la unica que puede mover los mensajes de una cola a otra dependiendo de
 * las confirmaciones recibidas por el servidor.
 */
public class QueueManagement {

    private final PriorityQueue<Message> queueToSend; // mensajes para enviar
    private final PriorityQueue<Message> sentQueue;  // mensajes enviados
    private final PriorityQueue<Message> receivedQueue;  // mensajes recibidos
    private final PriorityQueue<Message> confirmedQueue;  // mensajes confirmados

    private QueueManagement() {
        this.queueToSend = new PriorityQueue<>();
        this.sentQueue = new PriorityQueue<>();
        this.receivedQueue = new PriorityQueue<>();
        this.confirmedQueue = new PriorityQueue<>();
    }

    /**
     * Coloca un mensaje en la cola de listos para enviar.
     *
     * @param msg mensaje listo para enviar.
     */
    public void addMessageToSend(Message msg) {
        queueToSend.add(msg);
    }

    /**
     * Retorna un booleano indicando si hay mensajes listos para enviar.
     *
     * @return true si hay mensajes listos para enviar, sino false.
     */
    public boolean hasMessageToSend() {
        return (!queueToSend.isEmpty());
    }

    /**
     * Toma el primer mensaje listo para enviar de la cola.
     *
     * @return el primer mensaje con mayor prioridad.
     */
    public Message getMessageToSend() {
        if (!queueToSend.isEmpty()) {
            return queueToSend.poll();
        }

        return null;
    }

    /**
     * Coloca un mensaje en la cola de enviados.
     *
     * @param msg mensaje enviado.
     */
    public void setMessageSent(Message msg) {
        sentQueue.add(msg);
    }

    public void resendMessage(ResponseMessage resend) {
        Message aux = null;

        for (Message next : sentQueue) {
            if (next.getId() == resend.getMessageId()) {
                aux = next;
                queueToSend.add(next);
            }
        }

        if (aux != null) {
            sentQueue.remove(aux);
        }
    }

    public void changeToReceivedStatus(ResponseMessage msg) {
        Message aux = null;

        for (Message next : sentQueue) {
            if (next.getId() == msg.getMessageId()) {
                receivedQueue.add(next);
            }
        }

        if (aux != null) {
            sentQueue.remove(aux);
        }
    }

    public void changeToConfirmedStatus(ResponseMessage msg) {
        Message aux = null;

        for (Message next : receivedQueue) {
            if (next.getId() == msg.getMessageId()) {
                confirmedQueue.add(next);
            }
        }

        if (aux != null) {
            receivedQueue.remove(aux);
        }
    }

    /**
     * Utilizado para la implementacion del patron de diseño Singleton.
     *
     * @return una unica instancia de la clase.
     */
    public static QueueManagement getInstance() {
        return ConfigurationHolder.INSTANCE;
    }

    private static class ConfigurationHolder {

        private static final QueueManagement INSTANCE = new QueueManagement();
    }

}

package message;

import java.util.Date;

/**
 * Implementacion abstracta de un mensaje.
 * Todos los mensajes tienen en comun los atributos definidos en esta clase.
 * Implementa la interface Comparable para facilitar la priorizacion de los mensajes.
 * 
 */
public abstract class Message implements Comparable<Message> {

    private int id;
    private int imei;  // identificador unico del dispositivo.
    private int priority;
    private String type;
    private String subtype;
    private String operation; // operacion del mensaje (crear o actualizar).
    private Date timestamp;

    public Message() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getSubtype() {
        return subtype;
    }

    public int getImei() {
        return imei;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

    public String getOperation() {
        return operation;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * Implementacion de la comparacion entre mensajes.
     * @param other mensaje contra el cual comparar.
     *
     */
    @Override
    public int compareTo(Message other) {
        if (this.priority == other.getPriority()) {
            return 0;
        }
        return (this.priority < other.getPriority()) ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", imei: " + imei + ", type: " + type;
    }

}

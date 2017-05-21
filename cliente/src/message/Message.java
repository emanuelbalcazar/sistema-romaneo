package message;

import java.util.Date;

/**
 * Representa un mensaje enviado desde un dispositivo movil asociado a Romaneo
 *
 * @version 1.0
 */
public abstract class Message implements Comparable<Message> {

    private int id;
    private int imei;
    private int priority;
    private String type;
    private String subType;
    private String operation;
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

    public void setSubType(String subType) {
        this.subType = subType;
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

    public int getImei() {
        return imei;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }
    
    public String getSubType() {
        return subType;
    }

    public String getOperation() {
        return operation;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Message o) {
        if (this.priority == o.getPriority()) {
            return 0;
        }
        return (this.priority < o.getPriority()) ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", imei: " + imei + ", type: " + type;
    }

}

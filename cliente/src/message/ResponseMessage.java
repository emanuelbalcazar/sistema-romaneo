package message;

/**
 *
 * @author emanuel
 */
public class ResponseMessage {

    private int id;
    private int priority;
    private String type;
    private int messageId;
    private String messageType;
    private String messageSubType;
    private int imei;
    private String text;
    private String description;

    
    public ResponseMessage() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setMessageSubType(String messageSubType) {
        this.messageSubType = messageSubType;
    }
    
    public void setImei(int imei) {
        this.imei = imei;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }
    
    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getType() {
        return type;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getMessageSubType() {
        return messageSubType;
    }
    
    public int getImei() {
        return imei;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return " id= " + id + ", type= " + type + ", messageId= " + messageId + ", messageType= " + 
                messageType + ", messageSubType= " + messageSubType + ", imei= " + imei + ", description= " + description;
    }

}

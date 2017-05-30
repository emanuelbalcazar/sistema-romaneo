package logger;

import java.util.Date;

/**
 * Clase que representa un mensaje de log
 * @author emanuel
 */
public class LogMessage {

    private int messageId;  // id del mensaje a loggear
    private int imei;       // imei del mensaje a loggear
    private String source;  // fuente del log (cliente o servidor)
    private String level;   // nivel (info, warning, error, debug)
    private String status;  // estado del mensaje (generado, enviado, recibido, confirmado)
    private String messageType;  // tipo de mensaje a loggear
    private String description; 
    private Date timestamp;

    public LogMessage() {

    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getMessageId() {
        return messageId;
    }

    public int getImei() {
        return imei;
    }

    public String getSource() {
        return source;
    }

    public String getLevel() {
        return level;
    }

    public String getStatus() {
        return status;
    }

    public String getMessageType() {
        return messageType;
    }

    public String getDescription() {
        return description;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}

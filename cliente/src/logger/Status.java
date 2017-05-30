package logger;

/**
 * Enumerado de los estados de mensajes posibles.
 * 
 */
public enum Status {
    
    GENERATED("GENERADO"),
    SENT("ENVIADO"),
    RECEIVED("RECIBIDO"),
    CONFIRMED("CONFIRMADO"),
    ERROR("ERROR");
    
    private String status;
    
    Status(String status) {
        this.status = status;
    }
    
    public String getStatus() {
        return status;
    }
    
}

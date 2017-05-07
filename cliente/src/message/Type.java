package message;

/**
 * Enumerado de tipos de mensajes. 
 * 
 */
public enum Type {

    ROMANEO("ROMANEO"),
    GEOLOCATION("GEOLOCALIZACION"),
    TEXT("TEXTO"),
    ACK("ACK"),
    ERROR("ERROR");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

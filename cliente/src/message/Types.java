package message;

/**
 * Enumerado de tipos de mensajes. 
 * Los tipos de mensajes permiten identificar rapidamente el contenido del mismo.
 * 
 */
public enum Types {
    
    ROMANEO("ROMANEO"),
    GEOLOCATION("GEOLOCALIZACION"),
    TEXT("TEXTO"),
    OK("OK"),
    ERROR("ERROR");

    private final String type;

    Types(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
}

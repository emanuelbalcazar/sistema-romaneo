package message;

/**
 * Enumerado de tipos de mensajes. 
 * 
 * @author emanuel
 */
public enum Type {

    START_ROMANEO("INICIO DE ROMANEO"),
    BALE("FARDO"),
    QUALITY_SAMPLE("MUESTRA DE CALIDAD"),
    QUALITY_SAMPLE_CLOSURE("CIERRE DE MUESTRA DE CALIDAD"),
    ROMANEO_CLOSURE("CIERRE DE ROMANEO"),
    GEOLOCATION("GEOLOCALIZACION"),
    TEXT("TEXTO"),
    ACK("ACK");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

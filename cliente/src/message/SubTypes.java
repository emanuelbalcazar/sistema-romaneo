package message;

/**
 * Enumerado de sub-tipos de mensajes. 
 * Los sub-tipos de mensajes son clasificaciones de los tipos de mensajes, cada
 * sub-tipo en un mensaje representa una operacion especifica.
 * 
 */
public enum SubTypes {

    START_ROMANEO("INICIO DE ROMANEO"),
    BALE("FARDO"),
    QUALITY_SAMPLE("MUESTRA DE CALIDAD"),
    QUALITY_SAMPLE_CLOSURE("CIERRE DE MUESTRA DE CALIDAD"),
    ROMANEO_CLOSURE("CIERRE DE ROMANEO"),
    GEOLOCATION("GEOLOCALIZACION"),
    TEXT("TEXTO"),
    OK("OK"),
    ERROR("ERROR");

    private final String subType;

    SubTypes(String subType) {
        this.subType = subType;
    }

    public String getSubType() {
        return subType;
    }
}

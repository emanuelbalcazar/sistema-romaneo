package message;

/**
 *
 * @author emanuel
 */
public enum SubType {

    START_ROMANEO("INICIO DE ROMANEO"),
    BALE("FARDO"),
    QUALITY_SAMPLE("MUESTRA DE CALIDAD"),
    QUALITY_SAMPLE_CLOSURE("CIERRE DE MUESTRA DE CALIDAD"),
    END_ROMANEO("CIERRE DE ROMANEO"),
    ERROR("ERROR"),
    ACK("ACK");

    private final String subType;

    SubType(String subType) {
        this.subType = subType;
    }

    public String getSubType() {
        return subType;
    }
}

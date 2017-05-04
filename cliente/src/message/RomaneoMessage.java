package message;

/**
 * Representa un mensaje relacionado a un Romaneo.
 *
 */
public class RomaneoMessage extends Message {

    private int idRomaneo;
    private String producer;    // Productor
    private String establishment;   // Establecimiento
    private String contractor;  // Contratista
    private int amountOfAnimals;    // Cantidad de animales

    public RomaneoMessage() {

    }

    public void setIdRomaneo(int idRomaneo) {
        this.idRomaneo = idRomaneo;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public void setAmountOfAnimals(int amountOfAnimals) {
        this.amountOfAnimals = amountOfAnimals;
    }

    public int getIdRomaneo() {
        return idRomaneo;
    }

    public String getProducer() {
        return producer;
    }

    public String getEstablishment() {
        return establishment;
    }

    public String getContractor() {
        return contractor;
    }

    public int getAmountOfAnimals() {
        return amountOfAnimals;
    }

}

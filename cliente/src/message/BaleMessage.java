package message;

/**
 * Representa un mensaje relacionado a un Fardo.
 * 
 */
public class BaleMessage extends Message {

    private int idBale;
    private int weight; // Peso
    private String category;    // Categoria
    private String woolType;    // Tipo de lana

    public BaleMessage() {

    }

    public void setIdBale(int idBale) {
        this.idBale = idBale;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setWoolType(String woolType) {
        this.woolType = woolType;
    }

    public int getIdBale() {
        return idBale;
    }

    public int getWeight() {
        return weight;
    }

    public String getCategory() {
        return category;
    }

    public String getWoolType() {
        return woolType;
    }

}

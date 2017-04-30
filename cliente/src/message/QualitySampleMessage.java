package message;

/**
 *  Representa un mensaje relacionado a la Muestra de Calidad.
 * 
 * @author emanuel
 */
public class QualitySampleMessage extends Message {

    private int idQualitySample;
    private String categoryOfAnimal;    // Categoria del animal
    private String woolenClass;     // Clase de lana
    private int quantityBalesSampled;   // Cantidad de fardos muestreados
    private int numberOfSamplesTaken;    // Cantidad de muestras tomadas por cada fardo

    public QualitySampleMessage() {

    }

    public void setIdQualitySample(int idQualitySample) {
        this.idQualitySample = idQualitySample;
    }

    public void setCategoryOfAnimal(String categoryOfAnimal) {
        this.categoryOfAnimal = categoryOfAnimal;
    }

    public void setWoolenClass(String woolenClass) {
        this.woolenClass = woolenClass;
    }

    public void setQuantityBalesSampled(int quantityBalesSampled) {
        this.quantityBalesSampled = quantityBalesSampled;
    }

    public void setNumberOfSamplesTaken(int numberOfSamplesTaken) {
        this.numberOfSamplesTaken = numberOfSamplesTaken;
    }

    public int getIdQualitySample() {
        return idQualitySample;
    }

    public String getCategoryOfAnimal() {
        return categoryOfAnimal;
    }

    public String getWoolenClass() {
        return woolenClass;
    }

    public int getQuantityBalesSampled() {
        return quantityBalesSampled;
    }

    public int getNumberOfSamplesTaken() {
        return numberOfSamplesTaken;
    }
    
}

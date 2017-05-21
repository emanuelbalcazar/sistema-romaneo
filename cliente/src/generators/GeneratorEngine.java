package generators;

/**
 * Interfaz a implementar por el motor de generacion de mensajes.
 * 
 */
public interface GeneratorEngine {
    
    void startGenerators();
    
    void setImei(int imei);
    
}

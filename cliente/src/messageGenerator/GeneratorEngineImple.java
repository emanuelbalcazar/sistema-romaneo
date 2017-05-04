/*
 * Implementacion del motor de generacion de mensajes.
 * Instancia todos los generadores de mensajes disponibles 
 * e inicia la ejecucion.
 */
package messageGenerator;

public class GeneratorEngineImple implements GeneratorEngine {

    private final QueueManagement queueManagement;

    public GeneratorEngineImple(QueueManagement management) {
        this.queueManagement = management;
    }
    
    /**
     * Inicia la ejecucion de todos los generadores de mensajes disponibles.
     */
    @Override
    public void generateMessages() {
        MessageGeneratorGeneric romaneo = new RomaneoMessageGenerator();
        romaneo.setQueueManagement(queueManagement);
        romaneo.start();
    }

}

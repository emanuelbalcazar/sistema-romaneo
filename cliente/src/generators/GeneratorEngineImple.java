package generators;

import administrator.QueueManagement;

/**
 * Implementacion del motor de generacion de mensajes.
 * Se encarga de instanciar los generadores de mensajes disponibles e inicia
 * su ejecucion.
 */
public class GeneratorEngineImple implements GeneratorEngine {
    
    private final QueueManagement queueManagement;
    
    public GeneratorEngineImple(QueueManagement management) {
        this.queueManagement = management;
    }
    
    
    @Override
    public void startGenerators() {
        
        MessageGeneratorGeneric romaneo = new RomaneoMessageGenerator();
        romaneo.setQueueManagement(queueManagement);
        romaneo.start();

        MessageGeneratorGeneric texto = new TextMessageGenerator();
        texto.setQueueManagement(queueManagement);
        texto.start();
    }
    
}

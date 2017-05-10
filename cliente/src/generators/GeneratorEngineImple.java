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
    
    
   /** @Override
    public void startGenerators() {
        
        MessageGeneratorGeneric romaneo = new RomaneoMessageGenerator();
        romaneo.setQueueManagement(queueManagement);
        romaneo.start();
<<<<<<< Updated upstream

        MessageGeneratorGeneric texto = new TextMessageGenerator();
        texto.setQueueManagement(queueManagement);
        texto.start();
=======
    }*/
    
    @Override
    public void startGenerators() {
        MessageGeneratorGeneric geo = new GeolocationMessageGenerator();
        geo.setQueueManagement(queueManagement);
        geo.start();
>>>>>>> Stashed changes
    }
    
    
}

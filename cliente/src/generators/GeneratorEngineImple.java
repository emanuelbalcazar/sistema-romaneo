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
        
//        /*Intancia del generador de mensajes de romaneo y ejecucion
//        del mismo*/
//        MessageGeneratorGeneric romaneo = new RomaneoMessageGenerator();
//        romaneo.setQueueManagement(queueManagement);
//        romaneo.start();
//        /*Intancia del generador de mensajes de texto comun y ejecucion
//        del mismo*/
        MessageGeneratorGeneric texto = new TextMessageGenerator();
        texto.setQueueManagement(queueManagement);
        texto.start();
    }
    
}

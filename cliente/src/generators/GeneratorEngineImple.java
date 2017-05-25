package generators;

import administrator.QueueManagement;

/**
 * Implementacion del motor de generacion de mensajes. Se encarga de instanciar
 * los generadores de mensajes disponibles e inicia su ejecucion.
 */
public class GeneratorEngineImple implements GeneratorEngine {

    private final QueueManagement queueManagement;
    private int imei;

    public GeneratorEngineImple(QueueManagement management) {
        this.queueManagement = management;
    }
    
    @Override
    public void setImei(int imei) {
        this.imei = imei;
    }

    @Override
    public void startGenerators() {

        MessageGeneratorGeneric romaneo = new RomaneoMessageGenerator();
        romaneo.setQueueManagement(queueManagement);
        romaneo.setImei(imei);
        romaneo.start();

        MessageGeneratorGeneric texto = new TextMessageGenerator();
        texto.setQueueManagement(queueManagement);
        texto.setImei(imei);
        texto.start();

        MessageGeneratorGeneric geo = new GeolocationMessageGenerator();
        geo.setQueueManagement(queueManagement);
        geo.setImei(imei);
        geo.start();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrator;

import messageGenerator.QueueManagement;
import connectivity.ConnectivitySimulator;
import message.Message;
import messageGenerator.GeneratorEngine;
import messageGenerator.GeneratorEngineImple;

/**
 *
 * @author emanuel
 */
public class Administrator implements Runnable {

    private final ConnectivitySimulator connectivitySimulator;
    private final QueueManagement queueManagement;
    private final GeneratorEngine generators;
    private final Sender sender;
    private Thread thread;

    public Administrator(int imei) {
        this.connectivitySimulator = new ConnectivitySimulator();
        this.queueManagement = new QueueManagement();
        this.sender = new Sender();
        this.generators = new GeneratorEngineImple(queueManagement);
    }

    public void start() {
        inicialize();

        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }

    }

    private void inicialize() {
        generators.generateMessages();
        connectivitySimulator.start();
    }

    @Override
    public void run() {
        while (true) {
            waitTime();
            sendMessage();
        }
    }

    private void waitTime() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            System.err.println("Excepcion " + ex.getMessage());
        }
    }

    private void sendMessage() {
        if (connectivitySimulator.hasConnectivity() && queueManagement.hasMessageToSend()) {
            Message msg = queueManagement.getMessageToSend();
            sender.sendMessage(msg);
            System.out.println("Enviado mensaje : " + msg);
        } else {
            System.out.println("No hay nada para enviar");
        }
    }

}

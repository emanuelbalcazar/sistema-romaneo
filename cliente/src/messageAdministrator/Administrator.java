/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messageAdministrator;

import connectivity.ConnectivitySimulator;
import message.Message;
import messageGenerator.MessageGenerator;
import messageGenerator.MessageGeneratorImple;

/**
 *
 * @author emanuel
 */
public class Administrator implements Runnable {

    private final ConnectivitySimulator connectivitySimulator;
    private final QueueManagement queueManagement;
    private final MessageGenerator generators;
    private final Sender sender;
    private Thread thread;

    public Administrator(int imei) {
        this.connectivitySimulator = new ConnectivitySimulator();
        this.queueManagement = new QueueManagement();
        this.sender = new Sender();
        this.generators = new MessageGeneratorImple(queueManagement);
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

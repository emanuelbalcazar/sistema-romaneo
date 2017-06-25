package validators;

import administrator.QueueManagement;
import administrator.Sender;
import java.util.Date;
import logger.Logger;
import logger.Status;
import message.Message;
import message.Priority;
import message.ResponseMessage;
import message.TextMessage;
import message.Type;

/**
 * Clase encargada de analizar un mensaje recibido de parte del servidor. Los
 * mensajes pueden ser confirmaciones o mensajes de textos enviados desde el
 * servidor. En el caso de un mensaje de texto debe generar un Ack de mensaje
 * recibido.
 *
 */
public class Parser {

    private Sender sender;

    public Parser() {
        this.sender = new Sender();
    }

    /**
     * Parsea el mensaje y realiza alguna accion dependiendo del tipo de mensaje
     * recibido.
     *
     * @param msg mensaje de respuesta del servidor.
     */
    public void parseMessage(ResponseMessage msg) {
        if (msg.getType().equals("TEXTO")) {
            generateConfirmAck(msg);
        } else if (msg.getType().equals("REENVIAR")) {
            resendMessage(msg);
        } else {
            changeStatusMessage(msg);
        }
    }

    /**
     * Genera un ack de CONFIRMADO de mensaje de texto recibido por parte del
     * servidor.
     *
     * @param msg de texto enviado desde el servidor para el cliente.
     *
     */
    private void generateConfirmAck(ResponseMessage msg) {
        Message confirm = new TextMessage();

        confirm.setId(msg.getId());
        confirm.setImei(msg.getImei());
        confirm.setPriority(Priority.HIGH_PRIORITY.getPriority());
        confirm.setDescription("Mensaje de TEXTO: " + msg.getText() + " CONFIRMADO por el Cliente " + msg.getImei());
        confirm.setTimestamp(new Date());

        sender.sendMessage(confirm);
        Logger.getInstance().logInfo(confirm, "cliente " + msg.getImei(), Status.CONFIRMED.getStatus(), confirm.getDescription());
    }

    private void resendMessage(ResponseMessage msg) {
        Logger.getInstance().logInfo(adapteResponseMessage(msg), "cliente "  + msg.getImei(), Status.RESEND.getStatus(), "Mensaje a REENVIAR " + msg.getMessageType());
        QueueManagement.getInstance().resendMessage(msg);
    }

    private void changeStatusMessage(ResponseMessage msg) {
        if (msg.getType().equals(Status.RECEIVED.getStatus())) {
            QueueManagement.getInstance().changeToReceivedStatus(msg);
            Logger.getInstance().logInfo(adapteResponseMessage(msg), "cliente " + msg.getImei(), Status.RECEIVED.getStatus(), "Mensaje RECIBIDO " + msg.getMessageType() + " " + msg.getMessageSubType());
        } else if (msg.getType().equals(Status.CONFIRMED.getStatus())) {
            QueueManagement.getInstance().changeToConfirmedStatus(msg);
            Logger.getInstance().logInfo(adapteResponseMessage(msg), "cliente: " + msg.getImei(), Status.CONFIRMED.getStatus(), "Mensaje CONFIRMADO " + msg.getMessageType() + " " + msg.getMessageSubType());
        }
    }

    /**
     * Adapta un mensaje de tipo ResponseMessage.java a un mensaje valido para
     * el Logger.
     *
     * @param response mensaje de respuesta del servidor.
     * @return un mensaje que extiende de Message.java valido para ser utilizado
     * por el Logger.
     */
    private Message adapteResponseMessage(ResponseMessage response) {
        Message result = new TextMessage();  // Porque de texto? no se, pero es util.

        result.setId(response.getMessageId());
        result.setImei(response.getImei());
        result.setType(response.getMessageType());
        result.setSubType(response.getMessageSubType());
        result.setDescription(response.getDescription());

        return result;
    }

}

package message;

/**
 * Representa un mensaje relacionado a un Mensaje de Texto Comun.
 *
 */
public class TextMessage {
    
    private String text; // contenido del mensaje.

    public TextMessage() {
    
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
    
}

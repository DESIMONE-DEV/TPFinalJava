package Exceptions;

public class ColorNoExisteException extends RuntimeException {
    public ColorNoExisteException(String message) {
        super(message);
    }
    public ColorNoExisteException(){super("Color incorrecto");}
}

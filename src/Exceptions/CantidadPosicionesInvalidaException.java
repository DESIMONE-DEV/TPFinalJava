package Exceptions;

public class CantidadPosicionesInvalidaException extends RuntimeException {
    public CantidadPosicionesInvalidaException(String message) {
        super(message);
    }
    public CantidadPosicionesInvalidaException(){
        super("Cantidad de asientos no corresponde al juego");
    }
}

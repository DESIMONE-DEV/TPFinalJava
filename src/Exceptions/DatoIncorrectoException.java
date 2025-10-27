package Exceptions;

public class DatoIncorrectoException extends Exception {
    public DatoIncorrectoException(String message) {
        super(message);
    }
    public DatoIncorrectoException(){
        super("Datos incorrecto o mal escrito");
    }
}

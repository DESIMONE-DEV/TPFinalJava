package Exceptions;

public class CodigoIncorrectoException extends RuntimeException {
    public CodigoIncorrectoException(String message) {
        super(message);
    }
    public CodigoIncorrectoException() {
        super("La clave de creacion es invalida");
    }
}

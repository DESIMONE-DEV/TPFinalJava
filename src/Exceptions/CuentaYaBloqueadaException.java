package Exceptions;

public class CuentaYaBloqueadaException extends RuntimeException {
    public CuentaYaBloqueadaException(String message) {
        super(message);
    }
    public CuentaYaBloqueadaException() {
        super("La cuenta ya a sido bloqueada");
    }
}

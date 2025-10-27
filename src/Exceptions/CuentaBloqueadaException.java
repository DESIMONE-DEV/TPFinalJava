package Exceptions;

public class CuentaBloqueadaException extends RuntimeException {
    public CuentaBloqueadaException(String message) {
        super(message);
    }
    public CuentaBloqueadaException() {
        super("Su cuenta a sido bloqueada");
    }
}

package Exceptions;

public class CuentaExistenteException extends RuntimeException {
    public CuentaExistenteException(String message) {
        super(message);
    }
    public CuentaExistenteException() {
        super("La Cuenta ya existe");
    }
}

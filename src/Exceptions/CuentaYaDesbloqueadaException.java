package Exceptions;

public class CuentaYaDesbloqueadaException extends RuntimeException {
    public CuentaYaDesbloqueadaException(String message) {
        super(message);
    }
    public CuentaYaDesbloqueadaException() {
        super("La cuenta ya a sido desbloqueada");
    }
}

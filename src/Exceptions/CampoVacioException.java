package Exceptions;

public class CampoVacioException extends RuntimeException {
    public CampoVacioException(String message) {
        super(message);
    }
    public CampoVacioException() {
        super("No puede haber campos vacios");
    }
}

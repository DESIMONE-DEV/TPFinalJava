package Exceptions;

public class CaracteresMinimoException extends RuntimeException {
    public CaracteresMinimoException(String message) {
        super(message);
    }
    public CaracteresMinimoException() {
        super("No cumple con el minimo de caracteres");
    }
}

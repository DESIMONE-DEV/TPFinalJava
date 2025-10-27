package Exceptions;

public class CaracteresMaximoException extends RuntimeException {
    public CaracteresMaximoException(String message) {
        super(message);
    }
    public CaracteresMaximoException() {
        super("Excede la cantidad de caracteres");
    }
}

package Exceptions;

public class NumeroFueraDeRangoException extends RuntimeException {
    public NumeroFueraDeRangoException(String message) {
        super(message);
    }
    public NumeroFueraDeRangoException() {super("Numero fuera de rango");}
}

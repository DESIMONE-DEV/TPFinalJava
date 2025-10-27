package Exceptions;

public class NumeroFueraDeRango extends RuntimeException {
    public NumeroFueraDeRango(String message) {
        super(message);
    }
    public NumeroFueraDeRango() {super("Numero fuera de rango");}
}

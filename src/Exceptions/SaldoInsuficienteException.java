package Exceptions;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String message) {
        super(message);
    }
    public SaldoInsuficienteException(){
        super("No tiene suficiente saldo");
    }
}

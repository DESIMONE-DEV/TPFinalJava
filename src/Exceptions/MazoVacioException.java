package Exceptions;

public class MazoVacioException extends Exception {
    public MazoVacioException(String message) {
        super(message);
    }
    public MazoVacioException(){
        super("No hay mas cartas en el mazo");
    }
}

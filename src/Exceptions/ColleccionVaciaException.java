package Exceptions;

public class ColleccionVaciaException extends Exception {
    public ColleccionVaciaException(String message) {
        super(message);
    }
    public ColleccionVaciaException(){
        super("No existen datos en la colleccion");
    }
}

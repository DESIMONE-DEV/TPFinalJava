package Exceptions;

public class UsuarioContrañaIncorrectaException extends RuntimeException {
    public UsuarioContrañaIncorrectaException(String message) {
        super(message);
    }
    public UsuarioContrañaIncorrectaException() {
        super("Usuario o contraseña Incorrecto");
    }
}

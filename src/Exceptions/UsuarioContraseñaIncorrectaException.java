package Exceptions;

public class UsuarioContraseñaIncorrectaException extends RuntimeException {
    public UsuarioContraseñaIncorrectaException(String message) {
        super(message);
    }
    public UsuarioContraseñaIncorrectaException() {
        super("Usuario o contraseña Incorrecto");
    }
}

package Menu;

import Exceptions.ColleccionVaciaException;
import Exceptions.UsuarioContrañaIncorrectaException;
import Modelo.Gestores.GestorGenerico;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;

public class MenuPrincipal  {

    public static Usuario mLoginCliente(Long dni, String pass, GestorGenerico <Usuario> T)throws UsuarioContrañaIncorrectaException {
        for (Usuario User : T.getConjunto()) {
            if (User.getDni().equals(dni)){
                if(User.getPassword().equals(pass)){
                    return User;
                }
            }
        }
        throw new UsuarioContrañaIncorrectaException();
    }






}

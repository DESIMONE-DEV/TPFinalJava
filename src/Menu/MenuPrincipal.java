package Menu;

import Exceptions.ColleccionVaciaException;
import Modelo.Gestores.GestorGenerico;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;

public class MenuPrincipal <T>{

    public static Usuario mLoginCliente(Long dni, String pass, GestorGenerico <Usuario> T){
        Usuario u = null;
        for (Usuario User : T.getConjunto()) {
            if (User.getDni().equals(dni)){
                if(User.getPassword().equals(pass)){
                    return User;
                }
            }
        }
        return u;/// ACA VAN EXCEPCIONES!!!!!!!!!!!!!!!!!!!!!
    }

}

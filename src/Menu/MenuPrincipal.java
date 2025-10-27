package Menu;

import Exceptions.UsuarioContraseñaIncorrectaException;
import Modelo.Gestores.GestorGenerico;
import Modelo.Statics.CodPassword;
import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;

public class MenuPrincipal  {

    public static Usuario mLoginUsuario(Long dni, String pass, GestorGenerico <Usuario> T)throws UsuarioContraseñaIncorrectaException {
       pass = CodPassword.codificarPassword(pass);
        for (Usuario User : T.getConjunto()) {
            if (User.getDni().equals(dni)){
                if(User.getPassword().equals(pass)){
                    return User;
                }
            }
        }
        throw new UsuarioContraseñaIncorrectaException();
    }

    public static void adminOcliente(Usuario u){
        u instanceof Admin ? menuAdmin(u):menuCliente(u);

    }

    public static String creditos(){
        return "Gracias por Todo";

    }

    public static void (){
    }


}

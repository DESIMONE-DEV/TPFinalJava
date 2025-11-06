package Menu;

import Exceptions.UsuarioContraseñaIncorrectaException;
import Modelo.Gestores.GestorGenerico;
import Modelo.Statics.CodPassword;
import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;

public class MenuPrincipal  {

    ///----------------------- METODO PARA LOGUEAR------------------------------///
    ///
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
    ///----------------------- FIN METODO PARA LOGUEAR ---------------------------///
    ///
    ///
    /// --------------------METODO PARA IR A MENU ADMIN/CLIENTE--------------------///
    ///
    public static void adminOcliente(Usuario u){
       // u instanceof Admin ? menuAdmin(u):menuCliente(u);
    }
    /// -------------------FIN METODO PARA IR A MENU ADMIN/CLIENTE----------------///
    ///
    ///
    /// -------------------------METODO PARA CREAR ACCOUNT------------------------///
    ///
    ///
    /// -------------------------FIN METODO PARA CREAR ACCOUNT-------------------///
    public static String creditos(){
        return "Gracias por Todo";

    }


}

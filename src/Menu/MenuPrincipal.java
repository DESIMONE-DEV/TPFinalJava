package Menu;

import Exceptions.*;
import Modelo.Gestores.GestorGenerico;
import Modelo.Statics.CodPassword;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;


public class MenuPrincipal  {

    ///----------------------- METODO PARA LOGUEAR------------------------------///
    ///
    public static Usuario mLoginUsuario(int dni, String pass, GestorGenerico <Usuario> T)throws UsuarioContraseñaIncorrectaException {
       pass = CodPassword.codificarPassword(pass);
        for (Usuario User : T.getConjunto()) {
            if (User.getDni() == dni){
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
    public static void createAccountCLient (String nombre,int dni,String password,String cuentaBancaria)
            throws CampoVacioException, CaracteresMinimoException , CaracteresMaximoException {
        /// ---------SI PONE 1 ESPACIO O QUEDA VACIO-------/////
        if (nombre == null || nombre.equals("") ||
                password == null || password.equals("") ||
                cuentaBancaria == null || cuentaBancaria.equals("")) {
            throw new CampoVacioException("Alguno de los campos está vacío");
        }
        /// ---------- CARACTERES MINIMOS---------/////////
        if (nombre.length() < 4) {
            throw new CaracteresMinimoException("El nombre debe tener al menos 4 caracteres");
        }
        if (password.length() < 4) {
            throw new CaracteresMinimoException("La contraseña debe tener al menos 4 caracteres");
        }
        if (dni < 3000000){
            throw new CaracteresMinimoException("Ingrese un dni valido");
        }

        // Validar máximo de caracteres
        if (nombre.length() > 30) {
            throw new CaracteresMaximoException("El nombre no debe superar los 30 caracteres");
        }
        if (password.length() > 20) {
            throw new CaracteresMaximoException("La contraseña no debe superar los 20 caracteres");
        }
        if (dni > 100000000) {
            throw  new CaracteresMaximoException("Ingrese un dni valido");
        }

        Usuario u =new Cliente(nombre,dni,password,cuentaBancaria);
        GestionMenu.User.agregar(u);
    }
    ///
    /// -------------------------FIN METODO PARA CREAR ACCOUNT-------------------///
    ///
    /// -------------------------
    public static String creditos(){
        return "Gracias por Todo";

    }


}

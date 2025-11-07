package Menu;

import Exceptions.*;
import Modelo.Gestores.GestorGenerico;
import Modelo.Utiles.CodPassword;
import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;

import java.util.InputMismatchException;


public class MenuPrincipal  {

    ///----------------------- METODO PARA LOGUEAR------------------------------///
    ///
    public static Usuario mLoginUsuario(int dni, String pass, GestorGenerico<Usuario> T) throws UsuarioContraseñaIncorrectaException {
        pass = CodPassword.codificarPassword(pass);

        for (Usuario user : T.getConjunto()) {
            if (user.getDni() == dni && user.getPassword().equals(pass)) {
                return user;
            }
        }

        throw new UsuarioContraseñaIncorrectaException("DNI o contraseña incorrectos");
    }

    ///----------------------- FIN METODO PARA LOGUEAR ---------------------------///
    ///
    ///
    /// --------------------METODO PARA IR A MENU ADMIN/CLIENTE--------------------///
    ///
    public static void adminOcliente(Usuario u){
       u instanceof Admin ? menuInicialAdmin(u):menuInicialCliente(u);
    }
    /// -------------------FIN METODO PARA IR A MENU ADMIN/CLIENTE----------------///
    ///
    ///
    ////// -------------------------METODOS PARA CREAR ACCOUNT------------------------///
    ///
    /// -------------------------METODOS PARA CREAR ACCOUNT------------------------///
    public static void createAccountCLient (String nombre,int dni,String password,String cuentaBancaria)
            throws CampoVacioException, CaracteresMinimoException , CaracteresMaximoException, InputMismatchException {
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
        try {
            GestionMenu.User.agregar(u);
        } catch (CuentaExistenteException e) {
            System.out.println("La cuenta ya existe");
        }
    }
    ///
    /// /// -------------------------METODOS PARA CREAR ACCOUNT ADMINISTRADOR------------------------///
    ///
    public static void createAccountAdmin (String nombre,int dni,String password,String codigo)
            throws CampoVacioException, CaracteresMinimoException , CaracteresMaximoException , CodigoIncorrectoException {
        /// ---------SI PONE 1 ESPACIO O QUEDA VACIO-------/////
        if (nombre == null || nombre.equals("") ||
                password == null || password.equals("") ||
                codigo == null || codigo.equals("")) {
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
        if(!codigo.equals("ABC123")){
            throw new CodigoIncorrectoException("Solicite el Codigo a un Administrador");
        }
        Usuario b =new Admin(nombre,dni,password);

        try {
            GestionMenu.User.agregar(b);
        } catch (CuentaExistenteException e) {
            System.out.println("La cuenta ya existe");
        }
    }
    ///
    /// -------------------------FIN METODO PARA CREAR ACCOUNT-------------------///
    ///
    /// -------------------------METODO
    public static String creditos(){
        return "Gracias por Todo";

    }


}

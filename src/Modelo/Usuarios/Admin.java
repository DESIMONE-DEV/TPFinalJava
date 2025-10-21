package Modelo.Usuarios;

import java.util.UUID;

public class Admin extends Usuario {


    public Admin(String nombre, Long dni) {
        super(nombre,dni);
    }

    public Admin(UUID id) {
        super(id);
    }


    /// -----------FUNCIONES DE ADMIN---------------///
    //(Exception) Usuario no existente///no es un usuario es admin
    //Modificar fichas
    public void addFichas(){

    }

    public void
    //Bloquear usuario

    /// ---------FIN DE FUNCIONES DE ADMIN-------------////////


}

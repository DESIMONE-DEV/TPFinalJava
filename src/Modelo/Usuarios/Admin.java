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

    /// ---------FIN DE DUNCIONES DE ADMIN-------------////////
}

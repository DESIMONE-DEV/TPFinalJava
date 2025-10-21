package Modelo.Usuarios;

import Exceptions.ColleccionVaciaException;
import Modelo.Gestores.GestorGenerico;

import java.util.UUID;

public class Admin extends Usuario {


    public Admin(String nombre, Long dni ,String password) {
        super(nombre,dni, password);
    }

    public Admin(Long dni){
        super(dni);
    }

    public Admin(UUID id) {
        super(id);
    }


    /// -----------FUNCIONES DE ADMIN---------------///

    //Modificar fichas
    public Boolean addFichas(Cliente g,Double monto) {
       g.setSaldo(g.getSaldo()+monto);
        return true;
    }

    public Boolean addFichas(Cliente g,Double monto) {
        g.setSaldo(g.getSaldo()-monto);
        return true;
    }

    //Bloquear Cliente
    public  Boolean bloquear(Cliente g,Boolean b) {
        g.setEstadoCuenta(b);
    }

    /// ---------FIN DE FUNCIONES DE ADMIN-------------////////


}

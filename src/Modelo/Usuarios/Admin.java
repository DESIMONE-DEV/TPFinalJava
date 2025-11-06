package Modelo.Usuarios;

public class Admin extends Usuario {


    public Admin(String nombre, int dni ,String password) {
        super(nombre,dni, password);
    }

    public Admin(int dni){
        super(dni);
    }

    public Admin(int dni, String password) {
        super(dni, password);
    }

    /// -----------FUNCIONES DE ADMIN---------------///

    //Modificar fichas
    public Boolean agregarFichas(Cliente g,Double monto) {
       g.setSaldo(g.getSaldo()+monto);
        return true;
    }

    public Boolean quitarFichas(Cliente g,Double monto) {
        g.setSaldo(g.getSaldo()-monto);
        return true;
    }

    //Bloquear Cliente
    public  Boolean bloquear(Cliente g,Boolean b) {
        g.setEstadoCuenta(b);
        return true;
    }

    /// ---------FIN DE FUNCIONES DE ADMIN-------------////////


}

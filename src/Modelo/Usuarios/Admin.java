package Modelo.Usuarios;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

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
    ///
    /// ----------------------TO JSON  -----------------------------///

    @Override
    public JSONObject toJSON() throws JSONException {

        JSONObject objeto = new JSONObject();

        objeto.put("Tipo", "Admin");
        objeto.put("Nombre",getNombre());
        objeto.put("Id",getId());
        objeto.put("Dni",getDni());
        objeto.put("Password",getPassword());

        return objeto;

    }

    /// ---------------------FROM JSON ----------------------------///
    public Admin(JSONObject objeto) {
        super( objeto.getString("Nombre") ,
                UUID.fromString(objeto.getString("Id")),
                objeto.getInt("Dni"),
                objeto.getString("Password"));

    }

}

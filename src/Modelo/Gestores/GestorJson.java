package Modelo.Gestores;

import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;
import Modelo.Utiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;

public class GestorJson {

    /// -----------------------------METODO GUARDADO DE USUARIO A JSONARRAY----------------------///
    public static void guardarJson(HashSet<Usuario> User) {

        JSONArray jsonArray = new JSONArray();
        JSONObject objeto = new JSONObject();

        for(Usuario u : User) {
           if  (u instanceof Admin){
              objeto = ((Admin) u).toJSON();
           }
           else if (u instanceof Cliente) {
              objeto = ((Cliente)u).toJSON();
           }
            jsonArray.put(objeto);
        }

        JsonUtiles.grabarUnJson(jsonArray,"archivoUser.json");

    }

    /// ---------------------------  GRABADO DE JSONARRAY ------------------------------//////////////////
    ///
    ///





}

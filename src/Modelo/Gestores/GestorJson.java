package Modelo.Gestores;

import Menu.GestionMenu;
import Modelo.Stats.Estadistica;
import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;
import Modelo.Utiles.JsonUtiles;
import org.json.JSONArray;
import org.json.JSONException;
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

    /// --------------  METODO GUARDADO DE ESTADISTICAS ---------------------- ////

    public static void guardarEstadistica(JSONArray array) {
        try {
            JsonUtiles.grabarUnJson(array, "archivoStats.json");
        }catch(JSONException e){
            System.out.println("No se pudo guardar el archivo");
        }
    }

    /// ---------------------------  CARGA DE JSON------------------------------//////////////////
    ///
    public static  void cargajson() {
        JSONObject objeto = new JSONObject();

        String contenido = JsonUtiles.leer("archivoUser");
        JSONArray jsonArray = new JSONArray(contenido);

        for (int i = 0; i < jsonArray.length(); i++) {
            objeto = jsonArray.getJSONObject(i);
            String tipo = objeto.getString("Tipo");
            if (tipo.equals("Admin")) {
                GestionMenu.User.agregar(new Admin(objeto));
            }
            else if (tipo.equals("Cliente")) {
                GestionMenu.User.agregar(new Cliente(objeto));
            }
        }
    }
    /// ------------------- CARGA DE JSON ESTADISTICAS ------------------------- ////////////

    public static void cargarEstadisticas(){

        String contenido = JsonUtiles.leer("archivoStats");
        JSONArray array = new JSONArray(contenido);

        for(int i=0; i<array.length(); i++){
            GestionMenu.stats.agregarStats(new Estadistica(array.getJSONObject(i)));
        }
    }





}

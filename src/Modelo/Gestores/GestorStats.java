package Modelo.Gestores;

import Modelo.Stats.Estadistica;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class GestorStats {
    private ArrayList<Estadistica> stats;

    /// CONSTRUCTOR
    public GestorStats(){
        stats = new ArrayList<>();
    }

    /// GETTER AND SETTER
    public ArrayList<Estadistica> getStats() {
        return stats;
    }

    public void setStats(ArrayList<Estadistica> stats) {
        this.stats = stats;
    }

    /// METODOS

    public boolean agregarStats(Estadistica nueva){
        if(stats.add(nueva)){
            GestorJson.guardarEstadistica(toJSON());
            return true;
        }
        return false;
    }

    public String listarStats(String nombreJugador){
        StringBuilder msj = new StringBuilder(" - jugador -           - fecha -           - nombreMovimiento -            - monto -\n");
        for(Estadistica e : stats){
            if(e.getJugador().equals(nombreJugador))
                msj.append(e.toString() + "\n");
        }
        return msj.toString();
    }

    public ArrayList<Estadistica> arrayJugador(String nombreJugador){
        ArrayList<Estadistica> array = new ArrayList<>();

        for(Estadistica e : stats){
            if(e.getJugador().equals(nombreJugador)){
                array.add(e);
            }
        }
        return array;
    }

    public String listarStats(String nombreJugador, int cantidadStats){
        StringBuilder msj = new StringBuilder(" - jugador -           - fecha -           - nombreMovimiento -            - monto -\n");
        ArrayList<Estadistica> array = arrayJugador(nombreJugador);

        for(int i=0; i<cantidadStats; i++){
            msj.append(array.get(i).toString() + "\n");
        }
        return msj.toString();
    }

    public JSONArray toJSON() throws JSONException{
        JSONArray array = new JSONArray();
        for(Estadistica e : stats){
            array.put(e.toJSON());
        }
        return array;
    }
}

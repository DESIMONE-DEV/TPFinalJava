package Modelo.Stats;

import Exceptions.ColleccionVaciaException;
import Interfaces.IJson;
import Menu.GestionMenu;
import Modelo.Usuarios.Cliente;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class Estadistica implements IJson {
    private int jugador;
    private LocalDateTime fecha;
    private String nombreMovimiento;
    private double monto;


    /// CONSTRUCTORES
    public Estadistica(int jugador, String nombreMovimiento, double monto) {
        this.jugador = jugador;
        this.fecha = LocalDateTime.now();
        this.nombreMovimiento = nombreMovimiento;
        this.monto = monto;
    }

    public Estadistica(JSONObject objeto){
        jugador = objeto.getInt("jugador");
        fecha = LocalDateTime.parse(objeto.getString("fecha"));
        nombreMovimiento = objeto.getString("nombreMovimiento");
        monto = objeto.getDouble("monto");
    }

    /// ----------- GETTERS AND SETTERS --------------------------

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNombreMovimiento() {
        return nombreMovimiento;
    }

    public void setNombreMovimiento(String nombreMovimiento) {
        this.nombreMovimiento = nombreMovimiento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    /// ---------- FIN GETTERS AND SETTERS -------------------------------

    /// ------- METODOS --------------------------------------------------

    @Override
    public String toString() {
        try {
            return ((Cliente)GestionMenu.User.getDato(new Cliente(jugador))).getNombre() + "         " +
                    fecha.getDayOfMonth()+ '/' + fecha.getMonth() + '/' + fecha.getYear() + "      "
                    + fecha.getHour() + ':' + fecha.getMinute() + "                 " + nombreMovimiento +
                    "                $" + monto;
        } catch (ColleccionVaciaException e) {
            System.out.println("error");
        }
        return "";
    }

    @Override
    public JSONObject toJSON() throws JSONException{
        JSONObject objeto = new JSONObject();
        objeto.put("jugador", jugador);
        objeto.put("fecha", fecha.toString());
        objeto.put("nombreMovimiento", nombreMovimiento);
        objeto.put("monto", monto);

        return objeto;
    }
}

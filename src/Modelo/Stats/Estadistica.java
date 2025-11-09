package Modelo.Stats;

import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Objects;

public class Estadistica implements IJson {
    private String jugador;
    private LocalDateTime fecha;
    private String nombreMovimiento;
    private double monto;


    /// CONSTRUCTORES
    public Estadistica(String jugador, String nombreMovimiento, double monto) {
        this.jugador = jugador;
        this.fecha = LocalDateTime.now();
        this.nombreMovimiento = nombreMovimiento;
        this.monto = monto;
    }

    public Estadistica(JSONObject objeto){
        jugador = objeto.getString("jugador");
        fecha = LocalDateTime.parse(objeto.getString("fecha"));
        nombreMovimiento = objeto.getString("nombreMovimiento");
        monto = objeto.getDouble("monto");
    }

    /// ----------- GETTERS AND SETTERS --------------------------

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
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
    public boolean equals(Object o) {
        if (!(o instanceof Estadistica that)) return false;
        return Objects.equals(jugador, that.jugador) && Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jugador, fecha);
    }

    @Override
    public String toString() {
        return jugador + "         " + fecha + "         " + nombreMovimiento + "          $" + monto;
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

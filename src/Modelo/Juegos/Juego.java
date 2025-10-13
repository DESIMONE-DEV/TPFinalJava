package Modelo.Juegos;

import Interfaces.Pagador;

public abstract class Juego implements Pagador {
    private int id;
    private String nombre;
    private static int contadorId = 0;

    /// --------------------- CONSTRUCTORES --------------------------------------
    public Juego(String nombre) {
        this.nombre = nombre;
        id = contadorId;
        contadorId++;
    }

    public Juego() {
        nombre = "";
        id = contadorId;
        contadorId++;
    }
    /// -------------------- FIN CONSTRUCTORES -----------------------------------

    /// --------------------- GETTERS AND SETTERS -------------------------------
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public int getId() {
        return id;
    }
    /// -------------------- FIN GETTERS AND SETTERS -----------------------------

    /// --------------------------- METODOS --------------------------------------
}

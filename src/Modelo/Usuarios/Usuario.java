package Modelo.Usuarios;

import Modelo.Utiles.CodPassword;

import java.util.Objects;
import java.util.UUID;

public abstract class Usuario {
    private UUID id;
    private String nombre;
    private int dni;
    private String password;



    ///---------------------- CONSTRUCTORES--------------------- ///
    /// ----------------------COMPLETO PARA LOGIN---------------///
    ///
    public Usuario(String nombre, int dni,String password) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.dni = dni;
        this.password = CodPassword.codificarPassword(password) ; ///Funcion que codifica password!!!!

    }
    /// -------------------------------------------------------------
    public Usuario(UUID id) {
        this.id = id;
    }
    public Usuario(int dni) {
        this.dni = dni;
    }

    public Usuario(int dni, String password) {
        this.dni = dni;
        this.password = CodPassword.codificarPassword(password) ;
    }
    ///
    ///---------------------FIN CONSTRUCTORES------------------------------///

    ///-----------------------GETTERS AND SETTERS ----------------------///
    ///

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    ///-------------------- FIN GETTERS AND SETTERS-------------------------///
    ///

    /// -------------------HASHCODE E EQUALS POR DNI------------------------///

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    ///
    /// ---------------- FIN DE HASCODE E EQUALS--------------------------------////
    ///
    ///
    ///-----------------METODO TO STRING ------------------------------////
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", dni=" + dni +
                ", password='" + password + '\'' +
                '}';
    }

    /// ---------------- FIN DE METODO TO STRING--------------------------------////

}

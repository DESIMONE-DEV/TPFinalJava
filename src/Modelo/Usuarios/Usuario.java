package Modelo.Usuarios;

import java.util.Objects;
import java.util.UUID;

public abstract class Usuario {
    private UUID id;
    private String nombre;
    private Long dni;
    private String password



    //---------------------- CONSTRUCTORES--------------------- //
    public Usuario(String nombre, Long dni,String password) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.dni = dni;
        this.password = password; ///Agregar funcion de ema!!!!!!!!!!!!!!!!! codifica pass!!!!

    }

    public Usuario(UUID id) {
        this.id = id;
    }
    public Usuario(Long dni) {
        this.dni = dni;
    }
    public Usuario() {

    }

    ///---------------------FIN CONSTRUCTORES------------------------------///

    ///-----------------------GETTERS AND SETTERS ----------------------///

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Long getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    ///-------------------- FIN GETTERS AND SETTERS-------------------------///

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }


}

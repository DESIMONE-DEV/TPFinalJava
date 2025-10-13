package Modelo.Usuarios;

import java.util.UUID;

public abstract class Usuario {
    private UUID id;
    private String nombre;
    private Long dni;


    //---------------------- CONSTRUCTORES--------------------- //
    public Usuario(String nombre, Long dni) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.dni = dni;
    }

    public Usuario(UUID id) {
        this.id = id;
    }
    public Usuario(Long dni) {
        this.dni = dni;
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

}

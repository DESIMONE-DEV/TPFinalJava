package Modelo.Juegos;

import java.util.UUID;

public class Ruleta {

    private UUID numeroSalidor:

    public Ruleta() {
        this.numeroSalidor = UUID.randomUUID();
    }

    public UUID getNumeroSalidor() {
        return numeroSalidor;
    }

    public int pleno (int numeroApostador){
        int pleno = 0;
        if(numeroApostador== numeroSalidor){
            return pleno+36;
        }
        return pleno;
    }

    public int calle
}

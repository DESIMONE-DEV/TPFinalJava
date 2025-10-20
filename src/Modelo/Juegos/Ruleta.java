package Modelo.Juegos;

import Enums.ENumerosRuleta;

import java.util.Random;
import java.util.UUID;

public class Ruleta {

    Random randomGenerador = new Random();
    private int numeroSalidor;
    private ENumerosRuleta enumRuleta;

    public Ruleta() {
        this.numeroSalidor = 0;
        this.enumRuleta=ENumerosRuleta.N1;
    }

    public int getNumeroSalidor() {
        return numeroSalidor;
    }

    public int girarLaRuleta(){
        numeroSalidor = randomGenerador.nextInt(38);
    return numeroSalidor;
    }
    public int pleno (int numeroApostado , int numeroSalidor) {
        int valor = 0;
        if (numeroSalidor == numeroApostado) {
             36;
            return valor;
        }
        return valor;
    }

    public int color (int numeroApostado , int numeroSalidor) {
        int valor = 0;
        ENumerosRuleta col = enumRuleta;

    }
}




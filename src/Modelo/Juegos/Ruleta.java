package Modelo.Juegos;

import Enums.ENumerosRuleta;
import Interfaces.IPagador;

import java.util.HashMap;
import java.util.Random;

public class Ruleta implements IPagador {

    private HashMap<String , Double> apuestas ;
    private int numeroSalidor;
    private ENumerosRuleta enumRuleta;
    Random randomGenerador = new Random();

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
    public double pleno ( int numeroSalidor) {
        double valor= 0;
        for( String key : apuestas.keySet() ){
            int num = Integer.parseInt( key );
            if( numeroSalidor == num ){
                valor += apuestas.get(key)*36;
            }
        }
        return valor;
    }

    public double color ( int numeroSalidor) {
        double valor = 0;
        ENumerosRuleta col = enumRuleta;

    }

    @Override
    public boolean pagarFichas() {
        return false;
    }
}




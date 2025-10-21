package Modelo.Juegos;

import Enums.ENumerosRuleta;
import Modelo.Usuarios.Cliente;

import java.util.*;

public class Ruleta extends Juego {

    private HashMap<String , Double> apuestas ;
    private int numeroSalidor;
    private ENumerosRuleta enumRuleta;
    Random randomGenerador = new Random();
    /// -------------------- CONSTRUCTORES -----------------------------------

    public Ruleta() {
        this.numeroSalidor = 0;
        this.enumRuleta=ENumerosRuleta.N1;
    }

    public int getNumeroSalidor() {
        return numeroSalidor;
    }
    /// -------------------- FIN CONSTRUCTORES -----------------------------------

    /// -------------------- METODOS -----------------------------------
    ///
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


    public double color (int numeroSalidor) {
        double valor = 0;
        List<Integer> rojo = Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27, 30,32,34,36);
        List<Integer> negro = Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35);
        String color = null;

        if(rojo.contains(numeroSalidor)){
            color = "rojo";
        }else if(negro.contains(numeroSalidor)){
            color = "negro";
        }else{
            color = "verde";
        }
        for(String key : apuestas.keySet() ){
            if(color.equals(key)){
                valor = apuestas.get(key) * 2;
            }
        }
        return valor;
    }

    public double docenas (int numeroSalidor) {
        double valor = 0;
        String docena = null;
        if(numeroSalidor > 0 && numeroSalidor <= 12){
            docena = "primera";
        }else if(numeroSalidor > 12 && numeroSalidor <= 24){
            docena = "segunda";
        }else{
            docena = "tercera";
        }
        for(String key : apuestas.keySet() ){
            if(docena.equals(key)){
                valor = apuestas.get(key)*3;
            }
        }
        return valor;
    }

    public double columna (int numeroSalidor) {
        double valor = 0;
        String columna = null;
        if(numeroSalidor %3 == 0 && numeroSalidor != 0) {
            columna = "tercer columna";
        }else if((numeroSalidor + 1) % 3 == 0){
            columna = "segunda columna";
        }else if((numeroSalidor + 2) % 3 == 0){
            columna = "primera columna";
        }else{
            columna = "Cero";
        }
        for(String key : apuestas.keySet() ){
            if(columna.equals(key)){
                valor = apuestas.get(key)*3;
            }
        }
        return valor;
    }

    public double menorMayor (int numeroSalidor) {
        double valor = 0;
        String menorMayor = null;
        if(numeroSalidor > 0 && numeroSalidor <= 18){
            menorMayor = "menor";
        }else if(numeroSalidor > 18 && numeroSalidor <= 36){
            menorMayor = "mayor";
        }
        for(String key : apuestas.keySet() ){
            if(menorMayor.equals(key)){
                valor = apuestas.get(key)*2;
            }

        }
        return valor ;
    }

    public double parImpar(int numeroSalidor) {
        double valor = 0;
        String parImpar = null;
        if(numeroSalidor / 2 == 0){
            parImpar = "par";
        }else if(numeroSalidor / 2 == 1){
            parImpar = "impar";
        }
        for(String key : apuestas.keySet() ){
            if(parImpar.equals(key)){
                valor = apuestas.get(key)*2;
            }
        }
        return valor;
    }


    @Override
    public void jugar(Cliente jugador) {

    }

    @Override
    public boolean pagarFichas(Cliente jugador) {
        return false;
    }
    /// -------------------- FIN METODOS -----------------------------------

}




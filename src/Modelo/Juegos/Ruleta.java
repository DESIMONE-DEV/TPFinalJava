package Modelo.Juegos;

import Enums.ENumerosRuleta;

import java.util.*;

public class Ruleta extends Juego {

    private HashMap<String, Double> apuestas;
    private int numeroSalidor;
    private ENumerosRuleta enumRuleta;
    Random randomGenerador = new Random();
    /// -------------------- CONSTRUCTORES -----------------------------------

    public Ruleta() {
        this.numeroSalidor = 0;
        this.enumRuleta = ENumerosRuleta.N1;
        this.apuestas = new HashMap<>();
        inicializarRuleta();
    }

    public int getNumeroSalidor() {
        return numeroSalidor;
    }
    /// -------------------- FIN CONSTRUCTORES -----------------------------------

    /// -------------------- METODOS -----------------------------------

    //Genera el numero saliente de la ruleta
    public int girarLaRuleta(){
        numeroSalidor = randomGenerador.nextInt(37);
        return numeroSalidor;
    }


    //Metodo para inicializacion de ruleta con valores 0
    private void inicializarRuleta() {
        for (int i = 1; i <= 36; i++) apuestas.put(String.valueOf(i), 0.0);

        String[] opciones = {"negro", "rojo", "primera docena", "segunda docena",
                "tercera docena", "primera columna", "segunda columna",
                "tercera columna", "mayor", "menor", "impar", "par"};

        for (String opcion : opciones) {
            apuestas.put(opcion, 0.0);
        }
    }

    public double pleno(int numeroSalidor) {
        double valor = 0;
        for (String key : apuestas.keySet()) {
            int num = Integer.parseInt(key);
            if (numeroSalidor == num) {
                valor += apuestas.get(key) * 36;

            }
        }
        return valor;
    }

    public double color (int numeroSalidor) {
        double valor = 0;
        List<Integer> rojo = Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36);
        List<Integer> negro = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);
        String color = null;

        if (rojo.contains(numeroSalidor)) {
            color = "rojo";
        } else if (negro.contains(numeroSalidor)) {
            color = "negro";
        } else {
            color = "verde";
        }
        for (String key : apuestas.keySet()) {
            if (color.equals(key)) {
                valor = apuestas.get(key) * 2;
            }
        }
        return valor;
    }

    public double docenas (int numeroSalidor) {
        double valor = 0;
        String docena = null;
        if (numeroSalidor > 0 && numeroSalidor <= 12) {
            docena = "primera docena";
        } else if (numeroSalidor > 12 && numeroSalidor <= 24) {
            docena = "segunda docena";
        } else {
            docena = "tercera docena";
        }
        for (String key : apuestas.keySet()) {
            if (docena.equals(key)) {
                valor = apuestas.get(key) * 3;
            }
        }
        return valor;
    }

    public double columna(int numeroSalidor) {

        double valor = 0;
        String columna = null;
        if (numeroSalidor % 3 == 0 && numeroSalidor != 0) {
            columna = "tercer columna";
        } else if ((numeroSalidor + 1) % 3 == 0) {
            columna = "segunda columna";
        } else if ((numeroSalidor + 2) % 3 == 0) {
            columna = "primera columna";
        } else {
            columna = "cero";
        }
        for (String key : apuestas.keySet()) {
            if (columna.equals(key)) {
                valor = apuestas.get(key) * 3;
            }
        }
        return valor;
    }

    public double menorMayor(int numeroSalidor) {

        double valor = 0;
        String menorMayor = null;

        if (numeroSalidor > 0 && numeroSalidor <= 18) {
            menorMayor = "menor";
        } else if (numeroSalidor > 18 && numeroSalidor <= 36) {
            menorMayor = "mayor";
        }
        for (String key : apuestas.keySet()) {
            if (menorMayor != null && menorMayor.equals(key)) {
                valor = apuestas.get(key) * 2;
            }

        }
        return valor;
    }

    public double parImpar(int numeroSalidor) {
        double valor = 0;
        String parImpar = null;

        if(numeroSalidor % 2 == 0){
            parImpar = "par";
        }else if(numeroSalidor % 2 == 1){
            parImpar = "impar";
        }
        for(String key : apuestas.keySet() ){
            if(parImpar != null && parImpar.equals(key)){
                valor = apuestas.get(key)*2;
            }
        }
        return valor;
    }

    public void apostar (String apuesta, double valor ) {
        apuestas.put (apuesta, valor);
    }

    @Override
    public double pagarFichas() {
        double ganancia = 0;
        numeroSalidor = girarLaRuleta();
        ganancia = pleno(numeroSalidor)+color(numeroSalidor)+docenas(numeroSalidor)
                +columna(numeroSalidor)+parImpar(numeroSalidor)+menorMayor(numeroSalidor);

        return ganancia;
    }
    /// -------------------- FIN METODOS -----------------------------------

}




package Modelo.Juegos;

import Enums.EValorCarta;
import Exceptions.MazoVacioException;
import Interfaces.IRepartidor;
import Modelo.Juegos.MazoCartas.Carta;
import Modelo.Juegos.MazoCartas.Mazo;

import java.util.ArrayList;
import java.util.List;

public class BlackJack extends Juego implements IRepartidor {

    private Mazo mazo;
    private List <Carta> banca ;
    private List<Carta> jugador;
    private final EValorCarta as = EValorCarta.AS;

    public BlackJack() {
        mazo = new Mazo();
        banca = new ArrayList<Carta>();
        jugador = new ArrayList<Carta>();
    }

    ///                        Getters and Setters

    public List<Carta> getBanca() {
        return banca;
    }

    public void setBanca(List<Carta> banca) {
        this.banca = banca;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public List<Carta> getJugador() {
        return jugador;
    }

    public void setJugador(List<Carta> jugador) {
        this.jugador = jugador;
    }

    public EValorCarta getAs() {
        return as;
    }
    public void setAs(EValorCarta as) {
    }

    public boolean buscarAs (Carta carta) {
        for (Carta c : banca) {
            if (c.getValor().equals(as)){
                return true;
            }
        }
        return false;
    }

    public int negra  (Carta carta) {
        if( carta.getValor().equals(11) || carta.getValor().equals(12) || carta.getValor().equals(13)) {
            return 10;
        }
        return carta.getValor().getValorNum();
    }
     public boolean agragar () throws MazoVacioException {
        return banca.add((Carta) mazo.repartir(1));
     }
    public int manoBanca () throws MazoVacioException {
        int suma = 0;
        boolean buscar = true;
        do {
            agragar();
            for (Carta carta : banca) {
                suma += negra(carta);
                if (suma > 21 && buscar == buscarAs(carta)) {
                    suma -= 10;
                }
            }
        } while (suma > 17);
        return suma ;
    }

    public int manoJugador (int suma ) throws MazoVacioException {

        boolean buscar = true;
        agragar();
        for (Carta carta : jugador) {
            suma += negra(carta);
            if (suma > 21 && buscar == buscarAs(carta)) {
                suma -= 10;
            }
        }
        return suma;
    }


    @Override
    public void repartir(int cantUsuarios) throws MazoVacioException {
        int suma = 0;
        banca.add((Carta) mazo.repartir(2));
        jugador.add((Carta)mazo.repartir(2));

        for (Carta carta : banca) {
            suma =+ carta.getValor().getValorNum();
            if( suma > 21){
                for (Carta carta2 : banca) {
                    if(carta2.equals(as)){

                    }
                }
                }
            }
        }


    @Override
    public double pagarFichas() {
        return 0;
    }
}

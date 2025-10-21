package Modelo.Juegos;

import Exceptions.MazoVacioException;
import Interfaces.IPagador;
import Interfaces.IRepartidor;
import Modelo.Juegos.MazoCartas.Carta;
import Modelo.Juegos.MazoCartas.Mazo;

import java.util.ArrayList;
import java.util.List;

public class TriPoker implements IPagador, IRepartidor {
    private Mazo mazo;
    private List<Carta> mano1;
    private List<Carta> mano2;
    private List<Carta> mano3;
    private List<Carta> manoBanca;
    private final int cantCartasJuego = 3;

    public TriPoker(){
        mazo = new Mazo();
        mano1 = new ArrayList<>();
        mano2 = new ArrayList<>();
        mano3 = new ArrayList<>();
    }

    /// ----------------------- GETTERS AND SETTERS ---------------------------------
    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public List<Carta> getMano1() {
        return mano1;
    }

    public void setMano1(List<Carta> mano1) {
        this.mano1 = mano1;
    }

    public List<Carta> getMano2() {
        return mano2;
    }

    public void setMano2(List<Carta> mano2) {
        this.mano2 = mano2;
    }

    public List<Carta> getMano3() {
        return mano3;
    }

    public void setMano3(List<Carta> mano3) {
        this.mano3 = mano3;
    }

    public List<Carta> getManoBanca() {
        return manoBanca;
    }

    public void setManoBanca(List<Carta> manoBanca) {
        this.manoBanca = manoBanca;
    }

    public int getCantCartasJuego() {
        return cantCartasJuego;
    }

    /// ----------------------- FIN GETTERS AND SETTERS ------------------------------

    /// ------------------------ METODOS ---------------------------------------------



    @Override
    public boolean pagarFichas() {
        return false;
    }

    @Override
    public void repartir(int cantUsuarios) throws MazoVacioException {
        mazo.mezclar();
            if (cantUsuarios == 1) {
                mano1 = mazo.repartir(cantCartasJuego);
                manoBanca = mazo.repartir(cantCartasJuego);
            }else if(cantUsuarios == 2){
                mano1 = mazo.repartir(cantCartasJuego);
                mano2 = mazo.repartir(cantCartasJuego);
                manoBanca = mazo.repartir(cantCartasJuego);
            }else if(cantUsuarios > 2){
                mano1 = mazo.repartir(cantCartasJuego);
                mano2 = mazo.repartir(cantCartasJuego);
                mano3 = mazo.repartir(cantCartasJuego);
                manoBanca = mazo.repartir(cantCartasJuego);
            }
    }

    public void recuperarMazo(){
        mazo.recibirCarta(mano1);
        mazo.recibirCarta(mano2);
        mazo.recibirCarta(mano3);

        mano1.clear();
        mano2.clear();
        mano3.clear();
    }


    /// --------------------------- FIN METODOS --------------------------------------

}

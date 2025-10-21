package Modelo.Juegos;

import Exceptions.MazoVacioException;
import Interfaces.IRepartidor;
import Modelo.Juegos.MazoCartas.Carta;
import Modelo.Juegos.MazoCartas.Mazo;

import java.util.ArrayList;
import java.util.List;

public class TriPoker extends Juego implements IRepartidor {
    private Mazo mazo;
    private ManoTriPoker mano1;
    private ManoTriPoker mano2;
    private ManoTriPoker mano3;
    private List<Carta> manoBanca;
    private final int cantCartasJuego = 3;

    public TriPoker(){
        mazo = new Mazo();
        mano1 = new ManoTriPoker();
        mano2 = new ManoTriPoker();
        mano3 = new ManoTriPoker();
        manoBanca = new ArrayList<>();
    }

    /// ----------------------- GETTERS AND SETTERS ---------------------------------
    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    public ManoTriPoker getMano1() {
        return mano1;
    }

    public void setMano1(ManoTriPoker mano1) {
        this.mano1 = mano1;
    }

    public ManoTriPoker getMano2() {
        return mano2;
    }

    public void setMano2(ManoTriPoker mano2) {
        this.mano2 = mano2;
    }

    public ManoTriPoker getMano3() {
        return mano3;
    }

    public void setMano3(ManoTriPoker mano3) {
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
    public void repartir(int cantUsuarios) throws MazoVacioException {
        mazo.mezclar();
            if (cantUsuarios == 1) {
                mano1.setCartas(mazo.repartir(cantCartasJuego));
                manoBanca = mazo.repartir(cantCartasJuego);
            }else if(cantUsuarios == 2){
                mano1.setCartas(mazo.repartir(cantCartasJuego));
                mano2.setCartas(mazo.repartir(cantCartasJuego));
                manoBanca = mazo.repartir(cantCartasJuego);
            }else if(cantUsuarios > 2){
                mano1.setCartas(mazo.repartir(cantCartasJuego));
                mano2.setCartas(mazo.repartir(cantCartasJuego));
                mano3.setCartas(mazo.repartir(cantCartasJuego));
                manoBanca = mazo.repartir(cantCartasJuego);
            }
    }

    public void recuperarMazo(){
        mazo.recibirCarta(mano1.getCartas());
        mazo.recibirCarta(mano2.getCartas());
        mazo.recibirCarta(mano3.getCartas());

        mano1.getCartas().clear();
        mano2.getCartas().clear();
        mano3.getCartas().clear();
    }

    @Override
    public double pagarFichas() {
        return 0;
    }

    /// --------------------------- FIN METODOS --------------------------------------

}

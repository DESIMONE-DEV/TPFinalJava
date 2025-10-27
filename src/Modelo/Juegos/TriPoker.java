package Modelo.Juegos;

import Exceptions.MazoVacioException;
import Interfaces.IRepartidor;
import Modelo.Juegos.MazoCartas.Carta;
import Modelo.Juegos.MazoCartas.ManoTriPoker;
import Modelo.Juegos.MazoCartas.Mazo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TriPoker extends Juego implements IRepartidor {
    private Mazo mazo;
    private ManoTriPoker mano1;
    private ManoTriPoker mano2;
    private ManoTriPoker mano3;
    private ManoTriPoker manoBanca;
    private final int cantCartasJuego = 3;

    public TriPoker(){
        mazo = new Mazo();
        mano1 = new ManoTriPoker();
        mano2 = new ManoTriPoker();
        mano3 = new ManoTriPoker();
        manoBanca = new ManoTriPoker();
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

    public ManoTriPoker getManoBanca() {
        return manoBanca;
    }

    public void setManoBanca(ManoTriPoker manoBanca) {
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
            if (cantUsuarios >= 1) {
                mano1.setCartas(mazo.repartir(cantCartasJuego));
                manoBanca.setCartas(mazo.repartir(cantCartasJuego));
            }
            if(cantUsuarios >=2)
                mano2.setCartas(mazo.repartir(cantCartasJuego));
            if(cantUsuarios >=3)
                mano3.setCartas(mazo.repartir(cantCartasJuego));
    }

    public void recuperarMazo(){
        mazo.recibirCarta(mano1.getCartas());
        mazo.recibirCarta(mano2.getCartas());
        mazo.recibirCarta(mano3.getCartas());

        mano1.getCartas().clear();
        mano2.getCartas().clear();
        mano3.getCartas().clear();
    }

    public boolean esPoker(ManoTriPoker mano){
        /// si las 3 cartas son iguales tiene poker
        if(mano.getCartas().get(0).getValor() == mano.getCartas().get(1).getValor() &&
                mano.getCartas().get(0).getValor() == mano.getCartas().get(2).getValor()){
            return true;
        }else{
            return false;
        }
    }

    public boolean esPar(ManoTriPoker mano){
        if(esPoker(mano)) /// si tiene poker no podria ser par
            return false;
        else{
            /// si alguna de las cartas es igual a otra en cuanto a valor, es par
            if(mano.getCartas().get(0).getValor() == mano.getCartas().get(1).getValor() ||
                    mano.getCartas().get(0).getValor() == mano.getCartas().get(2).getValor() ||
                    mano.getCartas().get(2).getValor() == mano.getCartas().get(1).getValor())
                return true;
            else
                return false;
        }
    }

    public boolean esColor(ManoTriPoker mano){
        /// si el palo de las 3 cartas es igual tiene color
        if(mano.getCartas().get(0).getPalo() == mano.getCartas().get(1).getPalo() &&
        mano.getCartas().get(0).getPalo() == mano.getCartas().get(2).getPalo())
            return true;
        else return false;
    }

    public boolean esEscalera(ManoTriPoker mano){
        Collections.sort(mano.getCartas()); /// ORDENO LAS CARTAS DE LA MANO
        /// COMPARO SI LA PRIMERA + 1 ES IGUAL A LA SEGUNDA Y LA SEGUNDA + 1 ES IGUAL A LA TERCERA
        if( (mano.getCartas().get(0).getValor().getValorNum() + 1) == mano.getCartas().get(1).getValor().getValorNum() &&
                (mano.getCartas().get(1).getValor().getValorNum() + 1) == mano.getCartas().get(2).getValor().getValorNum()){

            return true;
        }else return false;
    }

    public boolean esEscaleraColor(ManoTriPoker mano){
        if(esColor(mano) && esEscalera(mano)){
            return true;
        }else{
            return false;
        }
    }

    public int valorizarJuego(ManoTriPoker mano){
        if(esEscaleraColor(mano)){
            return 41;
        }else if(esPoker(mano)){
            return 31;
        }else if(esEscalera(mano)){
            return 7;
        }else if(esColor(mano)){
            return 5;
        }else if(esPar(mano)){
            return 2;
        }else{
            return 0;
        }
    }

    /**
    * METODO PARA SABER SI EL JUGADOR GANA, EMPATA O PIERDE
     *
     * @return -1 SI PIERDE, 0 SI HAY EMPATE, 1 SI GANA
    * */
    public int jugadorGana(ManoTriPoker mano){
        int valorJugador = valorizarJuego(mano);
        int valorBanca = valorizarJuego(manoBanca);

        if(valorJugador > valorBanca){
            return 1;
        }else if(valorJugador == valorBanca){
            Collections.sort(mano.getCartas());
            Collections.sort(manoBanca.getCartas());
            int cartaJug0 = mano.getCartas().get(0).getValor().getValorNum();
            int cartaJug1 = mano.getCartas().get(1).getValor().getValorNum();
            int cartaJug2 = mano.getCartas().get(2).getValor().getValorNum();

            int cartaBanca0 = manoBanca.getCartas().get(0).getValor().getValorNum();
            int cartaBanca1 = manoBanca.getCartas().get(1).getValor().getValorNum();
            int cartaBanca2 = manoBanca.getCartas().get(2).getValor().getValorNum();

            if(cartaJug2 > cartaBanca2){
                return 1;
            }else if(cartaJug2 == cartaBanca2){
                if(cartaJug1 > cartaBanca1){
                    return 1;
                }else if(cartaJug1 == cartaBanca1){
                    if(cartaJug0 > cartaBanca0){
                        return 1;
                    }else if(cartaJug0 == cartaBanca0){
                        return 0;
                    }else return -1;
                }else return -1;
            }else return -1;
        }else return -1;
    }

    public double pagarBonus(ManoTriPoker mano){
        /// Multiplico el bonus por el valor de pago de cada juego
        return mano.getBonus() * valorizarJuego(mano);
    }

    public double pagarBet(ManoTriPoker mano){
        return mano.getBet() * 2;
    }

    public double pagarAnte(ManoTriPoker mano){
        return mano.getAnte() * 2;
    }

    @Override
    public double pagarFichas() {
        double sumaPago = 0;
        /// PAGO LOS BONUS
        sumaPago += pagarBonus(mano1);
        sumaPago += pagarBonus(mano2);
        sumaPago += pagarBonus(mano3);

        /// PAGO CADA MANO SI ES QUE GANO
        if(jugadorGana(mano1) > 0){
            sumaPago += pagarAnte(mano1) + pagarBet(mano1);
        }else if(jugadorGana(mano1) == 0){ ///  SI HAY EMPATE LE DEVUELVO SU APUESTA
            sumaPago += mano1.getAnte() + mano1.getBet();
        }

        if(jugadorGana(mano2) > 0){
            sumaPago += pagarAnte(mano2) + pagarBet(mano2);
        }else if(jugadorGana(mano2) == 0){
            sumaPago += mano2.getAnte() + mano2.getBet();
        }

        if(jugadorGana(mano3) > 0){
            sumaPago += pagarAnte(mano3) + pagarBet(mano3);
        }else if(jugadorGana(mano3) == 0){
            sumaPago += mano3.getAnte() + mano3.getBet();
        }

        return sumaPago;
    }

    /// --------------------------- FIN METODOS --------------------------------------

}

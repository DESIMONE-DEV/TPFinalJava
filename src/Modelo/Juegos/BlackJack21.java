package Modelo.Juegos;

import Exceptions.MazoVacioException;
import Interfaces.IRepartidor;
import Modelo.Juegos.MazoCartas.Carta;
import Modelo.Juegos.MazoCartas.ManoBlackJack;
import Modelo.Juegos.MazoCartas.Mazo;

public class BlackJack21 extends Juego implements IRepartidor {
    private Mazo mazo;
    private ManoBlackJack mano1;
    private ManoBlackJack banca;
    private final int cantidadCartas = 2;
    private Carta carta;

    public BlackJack21()
    {
        mazo = new Mazo();
        mano1 = new ManoBlackJack();
        banca = new ManoBlackJack();
    }

    ///   ----------------------- GETTERS AND SETTERS ---------------------------------

    public ManoBlackJack getBanca() {
        return banca;
    }

    public void setBanca(ManoBlackJack banca) {
        this.banca = banca;
    }

    public int getCantidadCartas() {
        return cantidadCartas;
    }

    public ManoBlackJack getMano1() {
        return mano1;
    }

    public void setMano1(ManoBlackJack mano1) {
        this.mano1 = mano1;
    }

    public Mazo getMazo() {
        return mazo;
    }

    public void setMazo(Mazo mazo) {
        this.mazo = mazo;
    }

    /// ----------------------- FIN GETTERS AND SETTERS ---------------------------------


    @Override
    public void repartir() throws MazoVacioException {
        mazo.mezclar();
        mano1.setCarta(mazo.repartir(cantidadCartas));
        banca.setCarta(mazo.repartir(cantidadCartas));

    }

    public int manoBancar () throws MazoVacioException {
        int suma = 0;
        boolean V = true;
        for (int i = 0 ; i < cantidadCartas; i++) {
            if (banca.getCarta().get(i).getValor().getValorSimb().equals("A")) {

            }
        }
        return suma;
    }

    public void recuperarMazo (){
        mazo.recibirCarta(mano1.getCarta());
        mazo.recibirCarta(banca.getCarta());
    }

    @Override
    public double pagarFichas() {
        return 0;
    }
}

package Modelo.Juegos.MazoCartas;

import java.util.ArrayList;
import java.util.List;

public class  ManoBlackJack {
    private List<Carta> mano;
    private double blackJack;

    public ManoBlackJack () {
        mano = new ArrayList<>();
        this.blackJack = 0;
    }

    /// --------------------- GETTERS AND SETTERS -----------------------------

    public double getBlackJack() {
        return blackJack;
    }

    public void setBlackJack(double blackJack) {
        this.blackJack = blackJack;
    }

    public List<Carta> getCarta() {
        return mano;
    }

    public void setCarta(List<Carta> carta) {
        this.mano = carta;
    }

    ///   ..........................METODOS .................................


    public void agregarCarta(List<Carta> carta) {
        mano.addAll(carta);
    }
}

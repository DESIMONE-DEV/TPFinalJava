package Modelo.Juegos.MazoCartas;

import java.util.ArrayList;
import java.util.List;

public class  ManoBlackJack {
    private List<Carta> carta;
    private double blackJack;

    public ManoBlackJack () {
        carta = new ArrayList<>();
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
        return carta;
    }

    public void setCarta(List<Carta> carta) {
        this.carta = carta;
    }
}

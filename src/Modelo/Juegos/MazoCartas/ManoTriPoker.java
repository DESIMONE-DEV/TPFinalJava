package Modelo.Juegos.MazoCartas;

import java.util.ArrayList;
import java.util.List;

public class ManoTriPoker {
    private List<Carta> cartas;
    private double bonus;
    private double ante;
    private double bet;

    public ManoTriPoker(){
        cartas = new ArrayList<>();
        bonus = 0;
        ante = 0;
        bet = 0;
    }

    /// --------------------- GETTERS AND SETTERS -----------------------------

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getAnte() {
        return ante;
    }

    public void setAnte(double ante) {
        this.ante = ante;
    }

    public double getBet() {
        return bet;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    /// ------------------------------ FIN GETTERS AND SETTERS -----------------------------------

    /// ------------------------------ METODOS ---------------------------------------------------


    /// ------------------------------- FIN METODOS ----------------------------------------------
}

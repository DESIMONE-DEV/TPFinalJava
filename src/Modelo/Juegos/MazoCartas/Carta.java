package Modelo.Juegos.MazoCartas;
import Enums.EValorCarta;
import Enums.EPaloCarta;
public class Carta {
    private EPaloCarta palo;
    private String valor;

    public Carta(EPaloCarta palo, String valor) {
        this.palo = palo;
        this.valor = valor;
    }
    public EPaloCarta getPalo() {
        return palo;
    }
    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor + " de " + palo;
    }

}

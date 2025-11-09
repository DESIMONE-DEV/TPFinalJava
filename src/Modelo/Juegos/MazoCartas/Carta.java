package Modelo.Juegos.MazoCartas;
import Enums.EValorCarta;
import Enums.EPaloCarta;
public class Carta implements Comparable{
    private EPaloCarta palo;
    private EValorCarta valor;

    public Carta(EPaloCarta palo, EValorCarta valor) {
        this.palo = palo;
        this.valor = valor;
    }
    public EPaloCarta getPalo() {
        return palo;
    }
    public EValorCarta getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return valor.getValorSimb() + " " + palo.getPalo();
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Carta){
            Carta recibida = (Carta) o;
            return valor.getValorNum() - recibida.getValor().getValorNum();
        }
        return 0;
    }
}

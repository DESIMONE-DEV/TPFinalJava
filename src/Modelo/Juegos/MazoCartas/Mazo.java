package Modelo.Juegos.MazoCartas;
import java.security.spec.ECParameterSpec;
import java.util.HashSet;
import Enums.EValorCarta;
import Enums.EPaloCarta;

public class Mazo {
    private HashSet<Carta> mazo;

    public Mazo() {
        mazo = new HashSet<>();
        for(EPaloCarta palo: EPaloCarta.values()){
            for(EValorCarta valor: EValorCarta.values()){
                mazo.add(new Carta(palo, valor.getValorSimb()));
            }
        }
    }
    public void verMazo(){
        for(Carta carta: mazo){
            System.out.println(carta);
        }
    }
    /*

    public void repartir();
    public void mezclar()
    public void agarrarCarta()
    public void hayCartas()
    public void verMano()

     */
}
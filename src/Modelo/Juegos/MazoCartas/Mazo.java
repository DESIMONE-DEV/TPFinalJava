package Modelo.Juegos.MazoCartas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import Enums.EValorCarta;
import Enums.EPaloCarta;

public class Mazo {
    private Stack<Carta> mazo;

    public Mazo() {
        mazo = new Stack<>();
        for(EPaloCarta palo: EPaloCarta.values()){
            for(EValorCarta valor: EValorCarta.values()){
                mazo.push(new Carta(palo, valor.getValorSimb()));
            }
        }
    }
    /// --------------------- Metodos --------------------------------------

    //Funcion shuffle reorganiza el mazo aleatoriamente
    public void mezclar(){
        Collections.shuffle(mazo);
    }
    //Se manda entero por parametro, y se reparte dicha cantidad
    public List<Carta> repartir(int cantidad){
        List<Carta> mano = new ArrayList<>();
        for(int i = 0; i < cantidad && !mazo.isEmpty(); i++){
            mano.add(mazo.pop());
        }
        return mano;
    }
    public Carta agarrarCarta(){
        if(!mazo.isEmpty()){
            return mazo.pop();
        }else {
            System.out.println("No hay mas cartas");
            return null;
        }
    }
    public boolean hayCartas(){
        return !mazo.isEmpty();
    }
    public void verMazo(){
        for(Carta carta: mazo){
            System.out.println(carta);
        }
    }
    /// --------------------- Fin metodos --------------------------------------


}
package Modelo.Juegos.MazoCartas;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import Enums.EValorCarta;
import Enums.EPaloCarta;
import Exceptions.MazoVacioException;

public class Mazo {
    private Stack<Carta> mazo;

    public Mazo() {
        mazo = new Stack<>();
        for(EPaloCarta palo: EPaloCarta.values()){
            for(EValorCarta valor: EValorCarta.values()){
                mazo.push(new Carta(palo, valor));
            }
        }
    }
    /// --------------------- METODOS --------------------------------------

    //Funcion shuffle reorganiza el mazo aleatoriamente
    public void mezclar(){
        Collections.shuffle(mazo);
    }

    //Se manda entero por parametro, y se reparte dicha cantidad
    public List<Carta> repartir(int cantidad) throws MazoVacioException{
        List<Carta> mano = new ArrayList<>();
        for(int i = 0; i < cantidad; i++){
            if(!mazo.isEmpty()) {
                mano.add(mazo.pop());
            }else throw new MazoVacioException();
        }
        return mano;
    }

    public Carta agarrarCarta() throws MazoVacioException {
        if(!mazo.isEmpty()){
            return mazo.pop();
        }else {
            throw new MazoVacioException();
        }
    }

    public boolean hayCartas(){
        return !mazo.isEmpty();
    }

    public boolean recibirCarta(List<Carta> cartasRecibidas){
        return mazo.addAll(cartasRecibidas);
    }

    /// --------------------- FIN METODOS --------------------------------------


}
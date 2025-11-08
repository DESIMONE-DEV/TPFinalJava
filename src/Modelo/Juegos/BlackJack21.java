package Modelo.Juegos;

import Enums.EPaloCarta;
import Enums.EValorCarta;
import Exceptions.MazoVacioException;
import Interfaces.IRepartidor;
import Modelo.Juegos.MazoCartas.Carta;
import Modelo.Juegos.MazoCartas.ManoBlackJack;
import Modelo.Juegos.MazoCartas.Mazo;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

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
    }
    public boolean encontrarAs (Carta carta) {

        if(carta.getValor().getValorSimb().equals("A")) {
            return true;
        }
      return false;
    }

    public boolean cambiarValores (Carta carta) {
        if (carta.getValor().getValorSimb().equals("J") ||  carta.getValor().getValorSimb().equals("Q")
            || carta.getValor().getValorSimb().equals("K") ){
            return true;
        }
        return false;
    }

    public int manoBancar ( List<Carta> ban ) throws MazoVacioException {
        mazo.mezclar();
        mano1.agregarCarta(mazo.repartir(2));
        int suma = 0;
        boolean V = true;
        for (Carta carta : ban ){
            if ( true == encontrarAs(carta)){
                suma =+ 11 ;
            }else if ( true == cambiarValores(carta)){
                suma =+ 10;
            }else {
                suma =+ carta.getValor().getValorNum();
                if(suma >16 && suma <22) {
                    return suma;
                }
            }
        }
        if ( ban.get(0).getValor().getValorSimb().equals("A") && suma > 21){
            suma =- 10 ;
            banca.agregarCarta(mazo.repartir(1));
            ban.addAll(banca.getCarta());
            suma =+ ban.get(2).getValor().getValorNum();
        }else if ( ban.get(1).getValor().getValorSimb().equals("A") && suma > 21){
            return 0;
        }else{
            return suma;
        }
        return suma;
    }

    public int manoUsuario ( List<Carta>ban ,Carta carta ) throws MazoVacioException {
        int suma = 0;
        ban.add(carta);
        boolean V = true;
        for (Carta car : ban) {
            if ( true == encontrarAs(car)){
                suma =+ 11 ;
            }else if ( true == cambiarValores(car)){
                suma =+ 10;
            }else {
                suma =+ carta.getValor().getValorNum();
                return suma ;
            }
        }
        if ( ban.get(0).getValor().getValorSimb().equals("A") && suma > 21){
            suma =- 10 ;
            banca.agregarCarta(mazo.repartir(1));
            suma =+ ban.get(2).getValor().getValorNum();
        }else if ( ban.get(1).getValor().getValorSimb().equals("A") && suma > 21){
            return 0;
        }else{
            return suma;
        }
        return suma;
    }

    public int pedirCarta (List<Carta> r ) throws MazoVacioException {
        mazo.mezclar();
       r.addAll(mazo.repartir(1));
       int suma = manoUsuario(r,carta);
       return suma;
    }

    public void recuperarMazo (){
        mazo.recibirCarta(mano1.getCarta());
        mazo.recibirCarta(banca.getCarta());

        mano1.getCarta().clear();
        banca.getCarta().clear();
    }

    @Override
    public double pagarFichas() {
        return 0;
    }
}

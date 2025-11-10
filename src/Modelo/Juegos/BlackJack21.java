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
        mano1.agregarCarta(mazo.repartir(cantidadCartas));
        banca.agregarCarta(mazo.repartir(cantidadCartas));
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

    public int jugarBanca() throws MazoVacioException{
        int suma;
        int j;
        if(mazo.hayCartas()){
            do{
                j = 0;
                suma = 0;       /// INICIALIZO LAS VARIABLES PARA CADA VUELA

                for(int i = 0; i < banca.cantidadDeCartas(); i++){      ///  SUMO LAS CARTAS QUE TENGO
                    suma += banca.buscarCarta(i).getValor().getValorBlackJack();
                }
                while(suma > 21 && j < banca.cantidadDeCartas()){  /// SI TENGO MAS DE 21 Y TENGO UN AS, LO CONVIERTO EN 1 AL AS
                    if(encontrarAs(banca.buscarCarta(j))){
                        suma -= 10;
                    }
                    j++;
                }

                if(suma < 17){
                    banca.agregarCarta(mazo.repartir(1));   /// SI TODAVIA NO LLEGUE A 17, AGREGO CARTA Y HACE UNA NUEVA VUELTA
                }

            }while(suma < 17); /// CUANDO YA TENGO 17 O MAS, TERMINO DE AGREGAR CARTAS Y RETORNO EL NUMERO

            return suma;
        }else{
            throw new MazoVacioException();
        }
    }

    public int manoBancar ( ) throws MazoVacioException {
        int suma = 0;
        if (mazo.hayCartas() == false) {
            throw new MazoVacioException();
        } else {
            if (encontrarAs(banca.buscarCarta(0)) == true) { ///  si encuentra el A vale 11
                suma += 11;
            } else if (cambiarValores(banca.buscarCarta(0)) == true) { /// si encuentra una J Q K hace que valga 10
                suma += 10;
            } else {
                suma += banca.getCarta().get(0).getValor().getValorNum();
            }
            if (encontrarAs(banca.buscarCarta(1)) == true) { ///  si encuentra el A vale 11
                suma += 11;
            } else if (cambiarValores(banca.buscarCarta(1)) == true) { /// si encuentra una J Q K hace que valga 10
                suma += 10;
            } else {
                suma += banca.getCarta().get(1).getValor().getValorNum();
            }

            if (16 < suma && suma < 22) {
                return suma;
            } else {
                    if (suma < 17) {
                        mazo.mezclar();
                        banca.agregarCarta(mazo.repartir(1));
                        if (encontrarAs(banca.buscarCarta(2)) == true) { ///  si encuentra el A vale 11
                            suma += 11;
                        } else if (cambiarValores(banca.buscarCarta(2)) == true) { /// si encuentra una J Q K hace que valga 10
                            suma += 10;
                        } else {
                            suma += banca.getCarta().get(1).getValor().getValorNum();
                        }
                        if (16 < suma && suma < 22) {
                            return suma;
                        }
                        if (suma > 21) {
                            for (Carta c : banca.getCarta()) {
                                if (c.getValor().getValorSimb() == "A")
                                    suma -= 10;
                                if (16 < suma && suma < 22) {
                                    return suma;
                                }
                            }
                            if (suma < 17) {
                                mazo.mezclar();
                                banca.agregarCarta(mazo.repartir(1));
                                if (encontrarAs(banca.buscarCarta(3)) == true) { ///  si encuentra el A vale 11
                                    suma += 11;
                                } else if (cambiarValores(banca.buscarCarta(3)) == true) { /// si encuentra una J Q K hace que valga 10
                                    suma += 10;
                                } else {
                                    suma += banca.getCarta().get(3).getValor().getValorNum();
                                }
                                if (16 < suma && suma < 22) {
                                    return suma;
                                }
                                if (suma > 21) {
                                    if (encontrarAs(banca.buscarCarta(3)) == true) {
                                        suma -= 10;
                                        if (16 < suma && suma < 22) {
                                            return suma;
                                        }
                                    }

                                }
                            }

                        }
                    }

                    if (suma > 21) {
                        for (Carta c : banca.getCarta()) {
                            if (c.getValor().getValorSimb() == "A")
                                suma -= 10;
                            if (16 < suma && suma < 22) {
                                return suma;
                            }
                        }
                        if (suma == 2) {
                            suma += 10;
                        }
                        if (suma < 17) {
                            mazo.mezclar();
                            banca.agregarCarta(mazo.repartir(1));
                            if (encontrarAs(banca.buscarCarta(2)) == true) { ///  si encuentra el A vale 11
                                suma += 11;
                            } else if (cambiarValores(banca.buscarCarta(2)) == true) { /// si encuentra una J Q K hace que valga 10
                                suma += 10;
                            } else {
                                suma += banca.getCarta().get(2).getValor().getValorNum();
                        if (suma > 21) {
                            if (encontrarAs(banca.buscarCarta(2)) == true) {
                                suma -= 10;
                                if (16 < suma && suma < 22) {
                                    return suma;
                                }
                            }
                        }}
                            if (suma < 17) {
                                mazo.mezclar();
                                banca.agregarCarta(mazo.repartir(1));
                                if (encontrarAs(banca.buscarCarta(3)) == true) { ///  si encuentra el A vale 11
                                    suma += 11;
                                } else if (cambiarValores(banca.buscarCarta(3)) == true) { /// si encuentra una J Q K hace que valga 10
                                    suma += 10;
                                } else {
                                    suma += banca.getCarta().get(3).getValor().getValorNum();
                                }

                            if (suma > 21) {
                                if (encontrarAs(banca.buscarCarta(3)) == true) {
                                    suma -= 10;
                                    if (16 < suma && suma < 22) {
                                        return suma;
                                    }
                                }

                            }}
                        }
                    }
            }
        }
        return suma;
        }
    public int manoUsuario ( ) throws MazoVacioException {
        int suma = 0;
        boolean V = true;
        for (int i = 0 ; i < mano1.cantidadDeCartas() ; i++){
            Carta carta = mano1.buscarCarta(i);
            if ( true == encontrarAs(carta)){
                suma += 11 ;
            }else if ( true == cambiarValores(carta)){
                suma += 10;
            }else {
                suma +=carta.getValor().getValorNum();
            }
            if (suma == 22){
                mano1.getCarta().contains("A");
                suma -=10;
            }
        }
        return suma;
    }


    public int pedirCartaUsuario (int suma) throws MazoVacioException {
        int valor = 0;
        mazo.mezclar();
        mano1.agregarCarta(mazo.repartir(1));
        if(mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("A") ||
                mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("J") ||
                mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("Q") ||
                mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("K")){
            if (mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("A")) {
                suma += 11;
                valor  =11;
                if (suma > 21) {
                    suma -= 10;
                    valor =10;
                }
            }
            if (mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("J") ||
                    mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("Q") ||
                    mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("K")) {
                suma += 10;
                valor =10;
            }
        }else{
            suma += mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorNum();
            valor = mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorNum();
        }
        return valor;
    }

        public void recuperarMazo(){
            mazo.recibirCarta(mano1.getCarta());
            mazo.recibirCarta(banca.getCarta());

            mano1.getCarta().clear();
            banca.getCarta().clear();
        }
    public String listarBancaConJuego(){
        StringBuilder listaCartas = new StringBuilder();
        for (int i = 0 ; i < banca.cantidadDeCartas() ; i++){
            Carta carta = banca.buscarCarta(i);
            listaCartas.append(carta.toString());
            listaCartas.append(" - ");
        }
        return listaCartas.toString();
    }
    public String listarJugadorJuego(){
        StringBuilder listaCartas = new StringBuilder();
        for (int i = 0 ; i < mano1.cantidadDeCartas() ; i++){
            Carta carta = mano1.buscarCarta(i);
            listaCartas.append(carta.toString());
            listaCartas.append(" - ");
        }
        return listaCartas.toString();
    }
    @Override
    public double pagarFichas() {
        return 0;
    }
}

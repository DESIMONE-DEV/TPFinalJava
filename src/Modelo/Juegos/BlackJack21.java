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
        mazo.mezclar();                                         /// Mezclan cartas
        mano1.agregarCarta(mazo.repartir(cantidadCartas));      /// Reparte las cartas inicales 2 para cada uno
        banca.agregarCarta(mazo.repartir(cantidadCartas));
    }
    public boolean encontrarAs (Carta carta) {

        if(carta.getValor().getValorSimb().equals("A")) {         ///  Busca el AS Para cambiar valor a 11
            return true;
        }
      return false;
    }

    public boolean cambiarValores (Carta carta) {
        if (carta.getValor().getValorSimb().equals("J") ||  carta.getValor().getValorSimb().equals("Q")
            || carta.getValor().getValorSimb().equals("K") ){           /// Busca J Q K para cambiar valores por 10
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
    public int manoUsuario ( ) throws MazoVacioException {
        int suma = 0;                                                                    /// Primera 2 cartas del Jugador
        boolean V = true;
        for (int i = 0 ; i < mano1.cantidadDeCartas() ; i++){
            Carta carta = mano1.buscarCarta(i);
            if ( true == encontrarAs(carta)){               ///  Si encuentra el As pone q vale 11  por que en la primer parte del enum valia 1
                suma += 11 ;
            }else if ( true == cambiarValores(carta)){    ///  si encuentra J Q K   pone q vale 10
                suma += 10;
            }else {
                suma +=carta.getValor().getValorNum();     /// valor normal de la carta que no sean ninguna anteriores
            }
            if (suma == 22){
                mano1.getCarta().contains("A");           /// Si la suma vale mas que 22  resta 10 para q valga 1 el AS
                suma -=10;
            }
        }
        return suma;
    }


    public int pedirCartaUsuario (int suma) throws MazoVacioException {
        int valor = 0;
        mazo.mezclar();                                  /// se mezcla el mazo
        mano1.agregarCarta(mazo.repartir(1));           /// Agrega carta cada vez que se pide desde el menu Black Jack
        if(mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("A") ||
                mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("J") ||
                mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("Q") ||   /// Iguala valores
                mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("K")){   /// para pone el valor
            if (mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("A")) {  ///  corrsponiente
                suma += 11;
                valor  =11;
                if (suma > 21) {      /// suma y si la carta vale 10 y se pasa de 21 , el as vale 1
                    suma -= 10;
                    valor =10;
                }
            }
            if (mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("J") ||
                    mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("Q") ||
                    mano1.getCarta().get(mano1.cantidadDeCartas() - 1).getValor().getValorSimb().equals("K")) {
                suma += 10;
                valor =10;     /// Si vale J Q K  vale 10
            }
        }else{
            suma += mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorNum();  /// Carta con valor normal
            valor = mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorNum(); /// suma normal
        }
        return valor;
    }

        public void recuperarMazo(){                                     ///  Recupera el mazo despues de la partida
            mazo.recibirCarta(mano1.getCarta());
            mazo.recibirCarta(banca.getCarta());

            mano1.getCarta().clear();
            banca.getCarta().clear();
        }
    public String listarBancaConJuego(){                                   ///  ToString de la cartas que tiene la Banca
        StringBuilder listaCartas = new StringBuilder();
        for (int i = 0 ; i < banca.cantidadDeCartas() ; i++){
            Carta carta = banca.buscarCarta(i);
            listaCartas.append(carta.toString());
            listaCartas.append(" - ");
        }
        return listaCartas.toString();
    }
    public String listarJugadorJuego(){                                      ///  ToString de la cartas que tiene el Jugador
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

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

    public int manoBancar ( ) throws MazoVacioException {
        int suma = 0;
        boolean V = true;
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
                        if(suma == 2){
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
                            }
                        }
                        if (suma > 21) {
                            if (encontrarAs(banca.buscarCarta(2)) == true) {
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

    public int pedirCarta () throws MazoVacioException {
        int suma = manoUsuario();
        if (false == mazo.hayCartas()) {
            throw new MazoVacioException();
        } else
            mano1.agregarCarta(mazo.repartir(1));
        suma +=mano1.buscarCarta(2).getValor().getValorNum();
        if (suma > 21) {
            if (mano1.getCarta().get(0).getValor().getValorSimb().equals("A") && suma > 21) {
                suma -=10;
                mano1.agregarCarta(mazo.repartir(1));
                suma +=mano1.buscarCarta(2).getValor().getValorNum();
                return suma;
            } else if (mano1.getCarta().get(1).getValor().getValorSimb().equals("A") && suma > 21) {
                suma -=10;
                mano1.agregarCarta(mazo.repartir(1));
                suma +=mano1.buscarCarta(3).getValor().getValorNum();
                return suma;
            } else if (mano1.getCarta().get(2).getValor().getValorSimb().equals("A") && suma > 21) {
                suma -=10;
                mano1.agregarCarta(mazo.repartir(1));
                suma +=mano1.buscarCarta(4).getValor().getValorNum();
                return suma;
            }

        }
        return suma;
    }
    public int pedirCartaUsuario ( int suma) throws MazoVacioException {
        mazo.mezclar();
        mano1.agregarCarta(mazo.repartir(1));
        if(mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorSimb().equals("A")){
            suma +=11;
            if(suma > 21) {
                suma -=10;
            }
        }else if(mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorSimb().equals("J") ||
                mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorSimb().equals("Q") ||
                mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorSimb().equals("K") ){
            suma +=10;
        }else{
            suma += mano1.getCarta().get(mano1.cantidadDeCartas()-1).getValor().getValorNum();
        }
        return suma;
    }

        public void recuperarMazo(){
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

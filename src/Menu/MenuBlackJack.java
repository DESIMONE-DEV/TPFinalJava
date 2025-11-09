package Menu;

import Exceptions.MazoVacioException;
import Modelo.Juegos.BlackJack21;
import Modelo.Usuarios.Cliente;

import java.util.Scanner;

public class MenuBlackJack {
    public static Scanner scan = new Scanner(System.in);

    public static void start(Cliente jugador) {
        BlackJack21 blackJack21 = new BlackJack21();
        int opcion = 0;
        String salir = "s";
        int sumaJugador=0;
        int sumaBanca=0;
        double apostar=0;
        double pago=0;

        System.out.println(".............BIENVENIDOS....A....BLACKJACK.......21.........");
        System.out.println("\n\n\n");
        dibujoCarta();
        do {
            System.out.println("HAGA SU APUESTA PRECIONES  10");
            System.out.println("1-COMENZAR EL JEUEGO");
            System.out.println("4-VERIFICAR QUE SACO LA BANCA");
            System.out.println("5-COMPARA VALORES");
            opcion = scan.nextInt();
            scan.nextLine();


            switch (opcion) {
                case 10:

                    try {
                        System.out.println("Su saldo es = $ " + jugador.getSaldo());
                        System.out.println("cuanto desea apostar ? ");
                        apostar = scan.nextDouble();
                        scan.nextLine();
                        jugador.retirarSaldo(apostar);
                    }catch (Exception e) {
                        System.out.println("Saldo insuficiente");
                        break;
                    }
                    limpiar();
                case 1:
                    try {
                        System.out.println("SE MEZCLAN LAS CARTAS");
                        scan.nextLine();
                        System.out.println("EL PAGADOR REPARTE LAS CARTAS ");
                        blackJack21.repartir();

                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Aprete ENTER para continuar...");
                    scan.nextLine();
                    limpiar();
                case 2:
                    System.out.println("..........TUS......CARTAS.............");
                    try {
                        sumaJugador= blackJack21.manoUsuario();
                        System.out.println("SACASTE = " + sumaJugador+ " Puntos");
                        if (sumaJugador == 21) {
                            System.out.println("SACASTE BLACKJACK ............... SE PAGA POR EL 150%\n");
                            System.out.println("             ......................");
                            System.out.println("             ......GANADOR..21.....");
                            System.out.println("             ......................");
                            pago= apostar + (apostar * 1.5);
                            System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                            jugador.cargarSaldo(pago);

                        }
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    if(sumaJugador== 21) {
                        break;
                    }else{
                        System.out.println("\n\nDesea agregar otro carta ?s/n");
                        salir = scan.nextLine();
                        if(salir.equals("s")) {
                            System.out.println("APRETE ESPACIO PARA RECIBIR CARTA");
                            scan.nextLine();
                        }else {
                            break;
                        }
                    }
                case 3:
                    do {
                        try {
                            sumaJugador += blackJack21.pedirCartaUsuario(sumaBanca);
                            System.out.println("LA SUMA DE SUS CARTAS ES = " + sumaJugador + " Puntos");
                        } catch (MazoVacioException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("Quiere seguir pidiendo carta? s/n");
                        String r="f";
                        r=scan.nextLine();
                    }while( salir.equalsIgnoreCase("s"));
                    break;
                case 4:
                    try {
                        sumaBanca = blackJack21.manoBancar();
                        System.out.println("LA BANCA SACO = " + sumaBanca+ " Puntos");
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    case 5:
                        System.out.println("Puntos Jugador = "+sumaJugador);
                        System.out.println("Puntos Banca = "+sumaBanca);
                        if(sumaJugador <22 && sumaBanca >21) {
                            System.out.println("*****************************");
                            System.out.println("*********Jugador Gana********");
                            System.out.println("*****************************\n");
                            System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                            pago= apostar + apostar ;
                            jugador.cargarSaldo(pago);
                        }
                        if(sumaJugador >21 && sumaBanca <22) {
                            System.out.println("Banca Gana , vuelva a intentar");
                        }
                        if(sumaBanca >21 && sumaJugador >21) {
                            System.out.println("EMPATE");
                            jugador.cargarSaldo(apostar);
                        }
                        if (sumaBanca <22 && sumaJugador <22 && sumaBanca == sumaJugador) {
                            System.out.println("EMPATE");
                            jugador.cargarSaldo(apostar);
                        }
                        if(sumaJugador <22 && sumaBanca <22 ) {
                            if(sumaBanca<sumaJugador) {
                                System.out.println("*****************************");
                                System.out.println("*********Jugador Gana********");
                                System.out.println("*****************************\n");
                                System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                                pago= apostar + apostar ;
                                jugador.cargarSaldo(pago);
                            }else {
                                System.out.println("JUGADOR PIERDE");
                            }
                        }
                        break;

            }
            salir = "s";
        } while (salir.equalsIgnoreCase("s"));
    }

    public static void limpiar() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void dibujoCarta (){
        System.out.println("+----------+");
        System.out.println("| A        |");
        System.out.println("|          |");
        System.out.println("|    ♣♣    |");
        System.out.println("|   ♣  ♣   |");
        System.out.println("|    ♣♣    |");
        System.out.println("|          |");
        System.out.println("|        A |");
        System.out.println("+----------+");
    }
}
package Menu;

import Exceptions.MazoVacioException;
import Modelo.Juegos.BlackJack21;
import Modelo.Usuarios.Cliente;

import java.util.Scanner;

public class MenuBlackJack {
    public static final String RESET = "\u001B[0m";
    public static final String VERDE = "\u001B[32m";
    public static final String ROJO   = "\u001B[31m";
    public static Scanner scan = new Scanner(System.in);

    public static void start(Cliente jugador) {
        BlackJack21 blackJack21 = new BlackJack21();
        int opcion = 0;
        int op = 2;
        int salir=0;
        int sumaJugador=0;
        int sumaBanca=0;
        double apostar=0;
        double pago=0;

        System.out.println(VERDE+".............BIENVENIDOS....A....BLACKJACK.......21........."+RESET);
        dibujoCarta2();
        do {
            System.out.println("               HAGA SU APUESTA        ");
            try {
                System.out.println("SU SALDO = $ " + jugador.getSaldo());
                System.out.println("CUANTO DESEA APOSTAR ");
                apostar = scan.nextDouble();
                scan.nextLine();
                jugador.retirarSaldo(apostar);
            }catch (Exception e) {
                System.out.println("Saldo insuficiente");
                break;
            }
            limpiar();
            System.out.println(" ...............COMIENZA EL JEUEGO..............");

                    try {
                        System.out.println("SE MEZCLAN LAS CARTAS...");
                        System.out.println("EL PAGADOR REPARTE LAS CARTAS... ");
                        blackJack21.repartir();

                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(ROJO+"ENTER para continuar..."+RESET);
                    scan.nextLine();
                    limpiar();
                    System.out.println("..........TUS......CARTAS.............");
                    dibujoCarta();
                    System.out.println(blackJack21.listarJugadorJuego());
                    try {
                        sumaJugador= blackJack21.manoUsuario();
                        System.out.println("SACASTE = " + sumaJugador+ " Puntos");
                        if (sumaJugador == 21) {
                            System.out.println(VERDE);
                            System.out.println("*****************************");
                            System.out.println("*********Jugador Gana********");
                            System.out.println("*****************************\n");
                            System.out.println(RESET);
                            pago= apostar + (apostar+apostar * 0.5);
                            System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                            jugador.cargarSaldo(pago);

                        }
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    if(sumaJugador== 21) {
                        break;
                    }else{

                        System.out.println("DESEA PEDIR OTRA CARTA ?? Presione '1' ");
                        opcion = scan.nextInt();
                        scan.nextLine();
                        do{
                        switch (opcion) {
                            case 1:
                                try {
                                    sumaJugador += blackJack21.pedirCartaUsuario(sumaJugador);
                                    System.out.println(blackJack21.listarJugadorJuego());
                                    System.out.println("LA SUMA DE SUS CARTAS ES = " + sumaJugador + " Puntos");

                                    if (sumaJugador > 21) {
                                        System.out.println("USTED SE PASO DE 21 ");
                                        op=2;
                                    }else {
                                        System.out.println("Quiere seguir pidiendo carta? Presione '1' ");
                                        op = scan.nextInt();
                                        scan.nextLine();
                                    }
                                } catch (MazoVacioException e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                        }
                    }while(op ==1 );

                    try {
                        System.out.println(ROJO+"ENTER para continuar..."+RESET);
                        scan.nextLine();
                        sumaBanca = blackJack21.jugarBanca();
                        System.out.println(blackJack21.listarBancaConJuego());
                        System.out.println("LA BANCA SACO = " + sumaBanca+ " Puntos");
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                        System.out.println("LOS PUNTOS TOTALES FUERON........");
                            scan.nextLine();
                        System.out.println(blackJack21.listarJugadorJuego());
                        System.out.println("Puntos Jugador = "+sumaJugador);
                        System.out.println("\n\n");
                        System.out.println(blackJack21.listarBancaConJuego());
                        System.out.println("Puntos Banca = "+sumaBanca);
                        if(sumaJugador <22 && sumaBanca >21) {
                            System.out.println(VERDE);
                            System.out.println("*****************************");
                            System.out.println("*********Jugador Gana********");
                            System.out.println("*****************************\n");
                            System.out.println(RESET);
                            pago= apostar + apostar ;
                            System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                            jugador.cargarSaldo(pago);
                        }
                        if(sumaJugador >21 && sumaBanca <22) {
                            System.out.println("JUGADOR PIERDE =( ..... VUELVA A INTENTARLO");
                        }
                        if(sumaBanca >21 && sumaJugador >21) {
                            System.out.println("EMPATE");
                            jugador.cargarSaldo(apostar);
                        }
                        if(sumaJugador <22 && sumaBanca <22 ) {
                            if(sumaBanca<sumaJugador) {
                                System.out.println(VERDE);
                                System.out.println("*****************************");
                                System.out.println("*********Jugador Gana********");
                                System.out.println("*****************************\n");
                                System.out.println(RESET);
                                pago = apostar + apostar;
                                System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                                jugador.cargarSaldo(pago);
                            }else if ( sumaBanca == sumaJugador) {
                                System.out.println("EMPATE");
                                jugador.cargarSaldo(apostar);
                            }else{
                                System.out.println("JUGADOR PIERDE =( ..... VUELVA A INTENTARLO");
                            }
                        }
            }
                    blackJack21.recuperarMazo();
            System.out.println("QUIERE SEGUIR JUGANDO PRESIONE ? '1'");
                    salir = scan.nextInt();
                    scan.nextLine();
        } while (salir==1);
    }

    public static void limpiar() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void dibujoCarta (){
        System.out.println(VERDE);
        System.out.println("+----------+");
        System.out.println("| A        |");
        System.out.println("|          |");
        System.out.println("|    ♣♣    |");
        System.out.println("|   ♣  ♣   |");
        System.out.println("|    ♣♣    |");
        System.out.println("|          |");
        System.out.println("|        A |");
        System.out.println("+----------+");
        System.out.println(RESET);
    }

    public static void dibujoCarta2 (){
        System.out.println(ROJO);
        System.out.println("+----------+  "+"+----------+");
        System.out.println("| A        |  "+"| k        |");
        System.out.println("|          |  "+"|          |");
        System.out.println("|    ♣♣    |  "+"|    ♣♣    |");
        System.out.println("|   ♣  ♣   |  "+"|   ♣  ♣   |");
        System.out.println("|    ♣♣    |  "+"|    ♣♣    |");
        System.out.println("|          |  "+"|          |");
        System.out.println("|        A |  "+"|        kw |");
        System.out.println("+----------+  "+"+----------+");
        System.out.println(RESET);
    }
}
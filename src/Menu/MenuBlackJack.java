package Menu;

import Exceptions.MazoVacioException;
import Exceptions.SaldoInsuficienteException;
import Modelo.Juegos.BlackJack21;
import Modelo.Usuarios.Cliente;

import java.util.InputMismatchException;
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
                do {
                    System.out.println("CUANTO DESEA APOSTAR ");
                    apostar = scan.nextDouble();
                    scan.nextLine();
                    if(apostar<= 0){
                        System.out.println("Ingresa un numero positivo");
                    }
                }while (apostar <= 0 );
                if(jugador.getSaldo() < apostar){
                    throw new SaldoInsuficienteException();
                }else{
                    jugador.retirarSaldo(apostar);
                }


            limpiar();
            System.out.println(" ...............COMIENZA EL JUEGO..............");

            System.out.println("\n\n\n");
                    try {
                        System.out.println("SE MEZCLAN LAS CARTAS...\n");
                        System.out.println("EL PAGADOR REPARTE LAS CARTAS... ");
                        blackJack21.repartir();

                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println(ROJO+"ENTER para continuar..."+RESET);
                    scan.nextLine();
                    limpiar();
                    System.out.println("..........TUS......CARTAS.............");
                    System.out.println(blackJack21.listarJugadorJuego());
                    try {
                        sumaJugador= blackJack21.manoUsuario();
                        System.out.println("SACASTE = " + sumaJugador+ " Puntos");
                        if (sumaJugador == 21) {
                            System.out.println(VERDE);
                            System.out.println("*****************************");
                            System.out.println("*********Jugador Gana******** ESTO ES BLACKJACK ");
                            System.out.println("*****************************\n");
                            System.out.println(RESET);
                            pago= apostar + (apostar+apostar * 2);
                            System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                            jugador.cargarSaldo(pago);
                            GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                            GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", pago);

                        }
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    if(sumaJugador!= 21) {

                        try {
                            System.out.println("DESEA PEDIR OTRA CARTA ?? Presione si lo desea''1'' , sino lo desea presione cualquier otro numero");
                            opcion = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException e) {
                             opcion = 2;
                             scan.nextLine();   /// POR INGRESAR 1 , NO PIDE CARTA
                        }
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
                                        System.out.println("DESEA PEDIR OTRA CARTA ?? Presione si lo desea''1'' , sino lo desea presione cualquier otro numero");
                                        op = scan.nextInt();
                                        scan.nextLine();
                                    }
                                } catch (MazoVacioException e) {
                                    System.out.println(e.getMessage());
                                }catch (InputMismatchException e) {
                                    op = 2;
                                    scan.nextLine();
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
                        System.out.println(blackJack21.listarBancaConJuego());
                        System.out.println("Puntos Banca = "+sumaBanca);
                        System.out.println("\n\n");
                        System.out.println(blackJack21.listarJugadorJuego());
                        System.out.println("Puntos Jugador = "+sumaJugador);
                        System.out.println("\n\n");
                        if(sumaJugador <22 && sumaBanca >21) {
                            System.out.println(VERDE);
                            System.out.println("*****************************");
                            System.out.println("*********Jugador Gana********");
                            System.out.println("*****************************\n");
                            System.out.println(RESET);
                            pago= apostar + apostar ;
                            System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                            jugador.cargarSaldo(pago);
                            GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                            GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", pago);
                        }
                        if(sumaJugador >21 && sumaBanca <22) {
                            System.out.println("JUGADOR PIERDE =( ..... VUELVA A INTENTARLO");
                            GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                            GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", (-1)*apostar);
                        }
                        if(sumaBanca >21 && sumaJugador >21) {
                            System.out.println("EMPATE");
                            jugador.cargarSaldo(apostar);
                            GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                            GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", 0);
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
                                GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", pago);
                            }else if ( sumaBanca == sumaJugador) {
                                System.out.println("EMPATE");
                                jugador.cargarSaldo(apostar);
                                GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", 0);
                            }else{
                                System.out.println("JUGADOR PIERDE =( ..... VUELVA A INTENTARLO");
                                GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", (-1)*apostar);
                            }
                        }
            }
                    blackJack21.recuperarMazo();
            }catch (SaldoInsuficienteException e) {
                System.out.println("Saldo insuficiente");
                break;
            }catch (InputMismatchException e) {
                System.out.println("Ingresa un numero");
                scan.nextLine();
            }
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
        System.out.println("|          |");
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
        System.out.println("|         |  "+"| k        |");
        System.out.println("|          |  "+"|          |");
        System.out.println("|    ♣♣    |  "+"|    ♣♣    |");
        System.out.println("|   ♣  ♣   |  "+"|   ♣  ♣   |");
        System.out.println("|    ♣♣    |  "+"|    ♣♣    |");
        System.out.println("|          |  "+"|          |");
        System.out.println("|        A |  "+"|        k |");
        System.out.println("+----------+  "+"+----------+");
        System.out.println(RESET);
    }
}
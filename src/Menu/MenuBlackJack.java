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
        int menu = 0;
        int salirMenu = 0;
        int opcion = 0;
        int op = 2;
        int salir = 0;
        int sumaJugador = 0;
        int sumaBanca = 0;
        double apostar = 0;
        double pago = 0;
        int h=0;

        do{
        do {


            try {
                h=0;
                    System.out.println("1- JUGAR");
                    System.out.println("2- DESCRIPCION DEL JUEGO");    /// Menu del juego
                    System.out.println("0- SALIR");
                    menu = scan.nextInt();
                    scan.nextLine();
                }catch(InputMismatchException e){
                    System.out.println("ingrese un numero correcto");
                    h = 1;
                    scan.nextLine();
                }
            }while (h== 1);
            switch (menu) {
                case 1:                         /// Primera parte del juego donde el jugador elije que apostar
                    System.out.println(VERDE + ".............BIENVENIDOS....A....BLACKJACK.......21........." + RESET);
                    dibujoCarta2();
                    do {
                        System.out.println("               HAGA SU APUESTA        ");
                        try {
                            System.out.println("SU SALDO = $ " + jugador.getSaldo());  /// Muestra el salgo del Jugador
                            do {
                                System.out.println("CUANTO DESEA APOSTAR $");
                                apostar = scan.nextDouble();  /// Lo que aposto
                                scan.nextLine();
                                if (apostar <= 0) {
                                    System.out.println("Ingresa un numero positivo");
                                }
                            } while (apostar <= 0);
                            if (jugador.getSaldo() < apostar) {
                                throw new SaldoInsuficienteException();
                            } else {
                                jugador.retirarSaldo(apostar);
                            }


                            limpiar();
                            System.out.println(" ...............COMIENZA EL JUEGO.............."); /// comienza el juego

                            System.out.println("\n\n\n");
                            try {
                                System.out.println("SE MEZCLAN LAS CARTAS...\n");
                                System.out.println("EL PAGADOR REPARTE LAS CARTAS... ");
                                blackJack21.repartir();                         /// Se mezclan las cartas y se reparten

                            } catch (MazoVacioException e) {
                                System.out.println(e.getMessage());
                            }
                            System.out.println(ROJO + "ENTER para continuar..." + RESET);
                            scan.nextLine();
                            limpiar();
                            System.out.println("..........TUS......CARTAS.............");
                            System.out.println(blackJack21.listarJugadorJuego());  ///muestra toString de las cartas jugador
                            try {
                                sumaJugador = blackJack21.manoUsuario();            /// Suma las Cartas
                                System.out.println("SACASTE = " + sumaJugador + " Puntos");
                                if (sumaJugador == 21) {
                                    System.out.println(VERDE);
                                    System.out.println("*****************************");
                                    System.out.println("*********Jugador Gana******** ESTO ES BLACKJACK ");
                                    System.out.println("*****************************\n");
                                    System.out.println(RESET);
                                    pago = apostar + (apostar+ (apostar/2));               /// si saca Black Jack de una le paga 150%
                                    System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                                    jugador.cargarSaldo(pago);
                                    GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                    GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", pago);

                                }
                            } catch (MazoVacioException e) {
                                System.out.println(e.getMessage());
                            }
                            if (sumaJugador != 21) {

                                try {
                                    System.out.println("DESEA PEDIR OTRA CARTA ?? Presione si lo desea''1'' , sino lo desea presione cualquier otro numero");
                                    opcion = scan.nextInt();                                                    /// si presiona 1 pierde
                                    scan.nextLine();                                                            /// otra carta
                                } catch (InputMismatchException e) {
                                    opcion = 2;
                                    scan.nextLine();   /// POR INGRESAR 1 , NO PIDE CARTA
                                }
                                do {
                                    switch (opcion) {
                                        case 1:
                                            try {
                                                sumaJugador += blackJack21.pedirCartaUsuario(sumaJugador); /// Pide nueva Carta y suma
                                                System.out.println(blackJack21.listarJugadorJuego());    /// ToString de las cartas
                                                System.out.println("LA SUMA DE SUS CARTAS ES = " + sumaJugador + " Puntos");

                                                if (sumaJugador > 21) {
                                                    System.out.println("USTED SE PASO DE 21 ");
                                                    op = 2;
                                                } else {
                                                    System.out.println("DESEA PEDIR OTRA CARTA ?? Presione si lo desea''1'' , sino lo desea presione cualquier otro numero");
                                                    op = scan.nextInt();  /// Puede seguir pidiendo carta
                                                    scan.nextLine();
                                                }
                                            } catch (MazoVacioException e) {
                                                System.out.println(e.getMessage());
                                            } catch (InputMismatchException e) {
                                                op = 2;
                                                scan.nextLine();
                                            }

                                            break;
                                    }
                                } while (op == 1);

                                try {
                                    System.out.println(ROJO + "ENTER para continuar..." + RESET);
                                    scan.nextLine();
                                    sumaBanca = blackJack21.jugarBanca();              /// Suma las cartas de La Banca
                                    System.out.println(blackJack21.listarBancaConJuego()); /// ToString de Banca
                                    System.out.println("LA BANCA SACO = " + sumaBanca + " Puntos");
                                } catch (MazoVacioException e) {
                                    System.out.println(e.getMessage());
                                }
                                System.out.println("LOS PUNTOS TOTALES FUERON........");
                                scan.nextLine();
                                System.out.println(blackJack21.listarBancaConJuego());  /// ToString cartas de la Banca
                                System.out.println("Puntos Banca = " + sumaBanca);      /// Suma total de las cartas de banca
                                System.out.println("\n\n");
                                System.out.println(blackJack21.listarJugadorJuego());  /// ToString cartas Jugador
                                System.out.println("Puntos Jugador = " + sumaJugador); /// Suma total de las cartas Jugador
                                System.out.println("\n\n");
                                if (sumaJugador < 22 && sumaBanca > 21) {               /// APARTIR DE ES MOMENTO SE DEFINE EL PASE
                                    System.out.println(VERDE);
                                    System.out.println("*****************************");
                                    System.out.println("*********Jugador Gana********");  /// Jugador gana
                                    System.out.println("*****************************\n");
                                    System.out.println(RESET);
                                    pago = apostar + apostar;
                                    System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                                    jugador.cargarSaldo(pago);
                                    GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                    GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", pago);
                                }
                                if (sumaJugador > 21 && sumaBanca < 22) {
                                    System.out.println("JUGADOR PIERDE =( ..... VUELVA A INTENTARLO");   /// Jugador pierde
                                    GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                    GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", (-1) * apostar);
                                }
                                if (sumaBanca > 21 && sumaJugador > 21) {
                                    System.out.println("EMPATE");
                                    jugador.cargarSaldo(apostar);               /// EMAPTE Y DEVUELVE LO QUE APOSTO
                                    GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                    GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", 0);
                                }
                                if (sumaJugador < 22 && sumaBanca < 22) {
                                    if (sumaBanca < sumaJugador) {
                                        System.out.println(VERDE);
                                        System.out.println("*****************************");
                                        System.out.println("*********Jugador Gana********");      /// JUGADOR GANA
                                        System.out.println("*****************************\n");
                                        System.out.println(RESET);
                                        pago = apostar + apostar;
                                        System.out.println("Su pago es=$ " + pago + " Y ya fue acreditado");
                                        jugador.cargarSaldo(pago);
                                        GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                        GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", pago);
                                    } else if (sumaBanca == sumaJugador) {
                                        System.out.println("EMPATE");
                                        jugador.cargarSaldo(apostar);               /// EMAPTE Y DEVUELVE LO QUE APOSTO
                                        GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                        GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", 0);
                                    } else {
                                        System.out.println("JUGADOR PIERDE =( ..... VUELVA A INTENTARLO");
                                        GestionMenu.guardadoAutomatico();           /// GUARDADO DEL JUGADOR Y SU STAT
                                        GestionMenu.crearStats(jugador.getDni(), "Jugada BlackJack", (-1) * apostar);
                                    }
                                }
                            }
                            blackJack21.recuperarMazo();
                        } catch (SaldoInsuficienteException e) {
                            System.out.println("Saldo insuficiente");       /// Saldo insuficiente
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Ingresa un numero");       /// Por si ingresa un numero
                            scan.nextLine();
                        }
                        System.out.println("QUIERE SEGUIR JUGANDO PRESIONE ? '1'");
                        try {
                            salir = scan.nextInt();
                            scan.nextLine();
                        } catch (InputMismatchException e) {
                            salir = 2;
                            scan.nextLine();
                        }


                    } while (salir == 1);
                    break;
                case 2 :
                    descripcionDelJuego();
                    ///  Descripcion del juego
                    break;
                case 0 :
                    salirMenu = 3;
                    break;
            }

        }while(salirMenu != 3);
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

    public static  void descripcionDelJuego(){
        System.out.println(
                "Como Jugar:\n\n"+
                "Bienvenidos a black Jack . Es un juego facil de jugar donde el objetivo es\n " +
                "sacar mas puntos que la banca sin pasarse de lo permitido (21);\n" +
                "Se dan 2 cartas a cada uno , 2 para el jugador y 2 para la banca . Primero se verifica\n" +
                "las cartas del jugador , donde en la suma de sus cartas cuanto mas cerca de 21 esta , mas chances de ganar hay\n" +
                "se puede pedir mas cartas  si el numero es bajo , hay q ser inteligente en no pasarce ojo , si el jugador saca\n " +
                "21 directo con las 2 cartas iniciales gana , hace el juego perfecto que es BLACK JACK y se le paga el 150% \n" +
                "de la apuesta ,y si ganan por mayor suma en lo q va del juego se le paga el 100%........ si se pasa de 21\n"+
                "y la banca tambien , hay empate. Por otro lado la banca se planta cuando la suma de sus cartas es entre 17 y 21 \n" +
                "(frena en ese momento de pedir carta\n" +


              "Las cartas que se juegan al blackjack son las de una baraja inglesa estándar de 52 cartas\n" +
                "donde el :\n\n"+
                "A = Vale 11 pero puede valer 1 si se pasa de 21 en la suma\n"+
                "J , Q , K = Valen 10\n"+


                "Disfruta de Black Jack y la mejor forma de aprende es jugando\n"+
                "QUE TE DIVIERTAS;");
                scan.nextLine();
    }
}
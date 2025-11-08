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
        String salir = "p";
        int sumaJugador=0;
        int sumaBanca=0;

        System.out.println(".............BIENVENIDOS....A....BLACKJACK.......21.........");
        System.out.println("\n\n\n");
        dibujoCarta();
        do {
            System.out.println("1-COMENZAR EL JEUEGO");
            System.out.println("3-AGREGAR CARTAS");
            System.out.println("4-VERIFICAR QUE SACO LA BANCA");
            System.out.println("5-COMPARA VALORES");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
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
                    System.out.println("..........TUSTAR CARTAS.............");
                    try {
                        sumaJugador= blackJack21.manoUsuario();
                        System.out.println("SACASTE = " + sumaJugador+ " Puntos");
                        if (sumaJugador == 21) {
                            System.out.println("SACASTE BLACKJACK ............... SE PAGA POR EL 150%\n");
                            System.out.println("             ...................");
                            System.out.println("             ......GANADOR......");
                            System.out.println("             ...................");
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
                            sumaJugador = blackJack21.pedirCarta();
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
                    int suma = 0;
                    try {
                        suma = blackJack21.manoBancar();
                        System.out.println("LA BANCA SACO = " + suma + " Puntos");
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

            }

            System.out.println("DESEA Seguir PRESIONE s");
            salir = scan.nextLine();


        } while (salir.equalsIgnoreCase("s"));
    }

    public static void limpiar() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("\n\n\n\n\n\n\n\n\\n\n\n\n\n\n\n\n\n\n\n\n\n\\n\n\n\n\n\n\n\n\n");
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
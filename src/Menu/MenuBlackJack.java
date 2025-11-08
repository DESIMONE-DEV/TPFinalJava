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

        do {
            System.out.println("1-REPARTIR CARTAS");
            System.out.println("2-TUSTAR CARTAS");
            System.out.println("3-AGREGAR CARTAS");
            System.out.println("4-VERIFICAR QUE SACO LA BANCA");
            System.out.println("5-COMPARA VALORES");
            opcion = scan.nextInt();
            scan.nextLine();

            opcion = scan.nextInt();
            switch (opcion) {
                case 1:
                    try {
                        blackJack21.repartir();
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    int suma2 = 0;
                    try {
                        suma2 = blackJack21.manoUsuario();
                        System.out.println("SACASTE = " + suma2 + " Puntos");
                        if (suma2 == 21) {
                            System.out.println("SACASTE BLACKJACK ............... SE PAGA POR EL 150%");
                        }
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    int suma3 = 0;
                    try {
                        suma3 = blackJack21.pedirCarta();
                        System.out.println("LA SUMA DE SUS CARTAS ES = " + suma3);
                    } catch (MazoVacioException e) {
                        System.out.println(e.getMessage());
                    }
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
            System.out.println("DESEA SALIR PRESIONE s");


        } while (salir != "s");
    }
}

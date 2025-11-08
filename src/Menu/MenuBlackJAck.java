package Menu;

import Exceptions.MazoVacioException;
import Modelo.Juegos.BlackJack21;
import Modelo.Juegos.MazoCartas.Carta;
import Modelo.Usuarios.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuBlackJAck {
    public static Scanner scan = new Scanner(System.in);

    public static void start(Cliente jugador){
        BlackJack21 mano = new BlackJack21();
        BlackJack21 bancar = new BlackJack21();
        int  opcion = 0;

        System.out.println("Ingresa el opcion que desea buscar");
        switch (opcion){
            case 1:

                break;
        }

    }

    public static void repartirCartas (BlackJack21 rr ) throws MazoVacioException {
        List<Carta> banca = new ArrayList<>();
        int suma = 0;
       suma = rr.manoBancar(banca);
        System.out.println("La suma de la banca es: " + suma);

    }
}

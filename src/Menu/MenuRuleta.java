package Menu;

import Modelo.Juegos.Ruleta;
import Modelo.Usuarios.Cliente;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;
public class MenuRuleta {
    public static void Start(Cliente jugador) {

        Scanner sc = new Scanner(System.in);
        char opcion = ' ';
        Ruleta ruleta = new Ruleta();

        ruleta.partesRuleta("1", 0);
        ruleta.partesRuleta("2", 0);
        ruleta.partesRuleta("3", 0);
        ruleta.partesRuleta("4", 0);
        ruleta.partesRuleta("5", 0);
        ruleta.partesRuleta("6", 0);
        ruleta.partesRuleta("7", 0);
        ruleta.partesRuleta("8", 0);
        ruleta.partesRuleta("9", 0);
        ruleta.partesRuleta("10", 0);
        ruleta.partesRuleta("11", 0);
        ruleta.partesRuleta("12", 0);
        ruleta.partesRuleta("13", 0);
        ruleta.partesRuleta("14", 0);
        ruleta.partesRuleta("15", 0);
        ruleta.partesRuleta("16", 0);
        ruleta.partesRuleta("17", 0);
        ruleta.partesRuleta("18", 0);
        ruleta.partesRuleta("19", 0);
        ruleta.partesRuleta("20", 0);
        ruleta.partesRuleta("21", 0);
        ruleta.partesRuleta("22", 0);
        ruleta.partesRuleta("23", 0);
        ruleta.partesRuleta("24", 0);
        ruleta.partesRuleta("25", 0);
        ruleta.partesRuleta("26", 0);
        ruleta.partesRuleta("27", 0);
        ruleta.partesRuleta("28", 0);
        ruleta.partesRuleta("29", 0);
        ruleta.partesRuleta("30", 0);
        ruleta.partesRuleta("31", 0);
        ruleta.partesRuleta("32", 0);
        ruleta.partesRuleta("33", 0);
        ruleta.partesRuleta("34", 0);
        ruleta.partesRuleta("35", 0);
        ruleta.partesRuleta("36", 0);
        ruleta.partesRuleta("negro", 0);
        ruleta.partesRuleta("rojo", 0);
        ruleta.partesRuleta("primera docena", 0);
        ruleta.partesRuleta("segunda docena", 0);
        ruleta.partesRuleta("tercera docena", 0);
        ruleta.partesRuleta("primera columna", 0);
        ruleta.partesRuleta("segunda columna", 0);
        ruleta.partesRuleta("tercera columna", 0);
        ruleta.partesRuleta("mayor", 0);
        ruleta.partesRuleta("menor", 0);
        ruleta.partesRuleta("impar", 0);
        ruleta.partesRuleta("par", 0);

        System.out.println("Bienvenido a Ruleta");
        do {
            System.out.println("Desea jugar a este juego ? ");
            System.out.println(" s / n ");
            opcion = sc.next().charAt(0);
        } while (opcion != 's');
        do {
            System.out.println("Elija las opciones");
            System.out.println("1. Jugar plenos ");
            System.out.println("2. Color ");
            System.out.println("3. Docenas ");
            System.out.println("4. Columnas ");
            System.out.println("5. MayorMenor ");
            System.out.println("6. ParImpar ");


        }while (opcion != '1');

        }
    }


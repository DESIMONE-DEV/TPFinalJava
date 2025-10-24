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

        System.out.println("Bienvenido a Ruleta");

        do {
            System.out.println("Desea jugar a este juego ? ");
            System.out.println(" s / n ");
            opcion = sc.next().charAt(0);
        } while (opcion != 's' && opcion != 'n');

        if (opcion == 'n') return;

        //Menu con opciones
        do {
            System.out.println("Elija las opciones");
            System.out.println("1. Jugar plenos ");
            System.out.println("2. Color ");
            System.out.println("3. Docenas ");
            System.out.println("4. Columnas ");
            System.out.println("5. MayorMenor ");
            System.out.println("6. ParImpar ");
            System.out.println("7. Salir");

            opcion = sc.next().charAt(0);

            switch (opcion) {
                case 1:
                    System.out.println("Apuesta plenos:");
                    //metodo
                    break;
                case 2:
                    System.out.println("Apuesta de color:");
                    try {
                        System.out.println("Ingrese un color para apostar (rojo/negro): ");
                        String color = sc.nextLine().toLowerCase(); //convierte el string ingresado en minusculas

                        if(!color.equals("rojo") && !color.equals("negro")) {
                            throw new ColorNoExisteException();
                        }
                        System.out.println("Ingrese monto a apostar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();

                        ruleta.apostar(color, monto);
                        System.out.println("Apuesta realizada a " + color);
                    } catch (ColorNoExisteException e) {
                        System.out.println(/*Mensaje de la excepcion*/);
                        sc.nextLine();
                    }
                    break;
                case 3:
                    System.out.println("Apuesta de docenas:");
                    //metodo
                    break;
                case 4:
                    System.out.println("Apuesta de columnas:");
                    //metodo
                    break;
                case 5:
                    System.out.println("Apuesta de mayor o menor:");
                    //metodo
                    break;

                case 6:
                    System.out.println("Apuesta de par o impar:");
                    //metodo
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }while (opcion != '7');

        }
    }


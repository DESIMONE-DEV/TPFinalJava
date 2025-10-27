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
            System.out.println("7. Girar ruletas");
            System.out.println("8. Salir");

            opcion = sc.next().charAt(0);

            switch (opcion) {
                case 1:
                    System.out.println("Apuesta plenos:");
                    try {
                        System.out.println("Ingrese numero al que quiera apostar: ");
                        int num = sc.nextInt();
                        sc.nextLine();
                        if(num < 0 || num > 36){
                            throw new NumeroFueraDeRango();
                        }
                        System.out.println("Ingrese monto a apostar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();

                        ruleta.apostar(String.valueOf(num), monto);

                        System.out.println("Apuesta realizada a " + num);

                    } catch (RuntimeException e) {
                        throw new RuntimeException();
                        sc.nextLine();
                    }
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

                    } catch (RuntimeException e) {
                        throw new RuntimeException();
                        sc.nextLine();
                    }
                    break;

                case 3:
                    System.out.println("Apuesta de docenas:");
                    try {
                        System.out.println("Ingrese la docena a la que quiera apostar (1 / 2 / 3): ");
                        int num =  sc.nextInt();
                        sc.nextLine();

                        String doc = null;
                        if(num == 1){
                            doc = "primera docena";
                        }else if(num == 2){
                            doc = "segunda docena";
                        }else if(num == 3){
                            doc = "tercera docena";
                        }else{
                            throw new NumeroFueraDeRango();
                        }

                        System.out.println("Ingrese monto a apostar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();

                        ruleta.apostar(doc, monto);
                        System.out.println("Apuesta realizada a " + doc);
                    } catch (RuntimeException e) {
                        throw new RuntimeException();
                        sc.nextLine();
                    }
                    break;

                case 4:
                    System.out.println("Apuesta de columnas:");
                    try {
                        System.out.println("Ingrese la columna a la que quiera apostar (1 / 2 / 3): ");
                        int num =  sc.nextInt();
                        sc.nextLine();

                        String col = null;
                        if(num == 1){
                            col = "primera columna";
                        }else if(num == 2){
                            col = "segunda columna";
                        }else if(num == 3){
                            col = "tercera columna";
                        }else{
                            throw new NumeroFueraDeRango();
                        }

                        System.out.println("Ingrese monto a apostar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();

                        ruleta.apostar(col, monto);
                        System.out.println("Apuesta realizada a " + col);
                    } catch (RuntimeException e) {
                        throw new RuntimeException();
                        sc.nextLine();
                    }
                    break;

                case 5:
                    System.out.println("Apuesta de mayor o menor:");
                    try {
                        System.out.println("Escriba si quiere apostar a menor (1 - 18) o mayor (19 - 36): ");
                        String menorMayor = sc.nextLine().toLowerCase();

                        if(!menorMayor.equals("menor") && (!menorMayor.equals("mayor"))){
                            throw new DatoIncorrectoException();
                        }
                        System.out.println("Ingrese monto a apostar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();

                        ruleta.apostar(menorMayor, monto);
                        System.out.println("Apuesta realizada a " + menorMayor);
                    } catch (RuntimeException e) {
                        throw new RuntimeException();
                        sc.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("Apuesta de par o impar:");

                    try {
                        System.out.println("Escriba si quiere apostar a par o impar: ");
                        String parImpar = sc.nextLine().toLowerCase();

                        if(!parImpar.equals("par") && (!parImpar.equals("impar"))){
                            throw new DatoIncorrectoException();
                        }

                        System.out.println("Ingrese monto a apostar: ");
                        double monto = sc.nextDouble();
                        sc.nextLine();

                        ruleta.apostar(parImpar, monto);
                        System.out.println("Apuesta realizada a " + parImpar);
                    } catch (RuntimeException e) {
                        throw new RuntimeException();
                        sc.nextLine();
                    }
                    break;
                case 7:

                default:
                    System.out.println("Opcion invalida");
            }
        }while (opcion != '8');

    }
}


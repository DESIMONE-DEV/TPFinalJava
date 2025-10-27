package Menu;

import Exceptions.DatoIncorrectoException;
import Exceptions.NumeroFueraDeRango;
import Exceptions.SaldoInsuficienteException;
import Modelo.Juegos.Ruleta;
import Modelo.Usuarios.Cliente;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Scanner;

public class MenuRuleta {

        public static Scanner sc = new Scanner(System.in);

        public static void Start(Cliente jugador) {
            int salir = 1;

            char opcion = ' ';

            System.out.println("Bienvenido a Ruleta");

            menuInicial();
            do {
                System.out.println("Ingrese un numero: ");
                opcion = sc.next().charAt(0);
                switch (opcion) {
                    case 1:
                        Ruleta ruleta = new Ruleta();
                        ingresarApuestas(ruleta, jugador);
                        break;
                    case 0:
                        salir = 0;
                        return;
                }
            } while (salir == 1);


        }
        public static void menuInicial(){
            System.out.println("1. Jugar");
            System.out.println("0. Salir");
        }

        public static void ingresarApuestas(Ruleta ruleta, Cliente jugador) throws RuntimeException, SaldoInsuficienteException {
            //Menu con opciones

            int opcion;
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
                            if (num < 0 || num > 36) {
                                throw new NumeroFueraDeRango();
                            }
                            System.out.println("Ingrese monto a apostar: ");
                            double monto = sc.nextDouble();
                            sc.nextLine();

                            ruleta.apostar(String.valueOf(num), monto);

                            System.out.println("Apuesta realizada a " + num);

                        } catch (RuntimeException e) {
                            throw new RuntimeException();
                        } finally {
                            sc.nextLine();

                        }
                        break;

                    case 2:
                        System.out.println("Apuesta de color:");
                        try {
                            System.out.println("Ingrese un color para apostar (rojo/negro): ");
                            String color = sc.nextLine().toLowerCase(); //convierte el string ingresado en minusculas

                            if (!color.equals("rojo") && !color.equals("negro")) {
                                throw new DatoIncorrectoException();
                            }
                            System.out.println("Ingrese monto a apostar: ");
                            double monto = sc.nextDouble();
                            sc.nextLine();

                            ruleta.apostar(color, monto);
                            System.out.println("Apuesta realizada a " + color);

                        } catch (RuntimeException | DatoIncorrectoException e) {
                            throw new RuntimeException();
                        } finally {
                            sc.nextLine();
                        }
                        break;

                    case 3:
                        System.out.println("Apuesta de docenas:");
                        try {
                            System.out.println("Ingrese la docena a la que quiera apostar (1 / 2 / 3): ");
                            int num = sc.nextInt();
                            sc.nextLine();

                            String doc = null;
                            if (num == 1) {
                                doc = "primera docena";
                            } else if (num == 2) {
                                doc = "segunda docena";
                            } else if (num == 3) {
                                doc = "tercera docena";
                            } else {
                                throw new NumeroFueraDeRango();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            double monto = sc.nextDouble();
                            sc.nextLine();

                            ruleta.apostar(doc, monto);
                            System.out.println("Apuesta realizada a " + doc);
                        } catch (RuntimeException e) {
                            throw new RuntimeException();
                        } finally {
                            sc.nextLine();
                        }
                        break;

                    case 4:
                        System.out.println("Apuesta de columnas:");
                        try {
                            System.out.println("Ingrese la columna a la que quiera apostar (1 / 2 / 3): ");
                            int num = sc.nextInt();
                            sc.nextLine();

                            String col = null;
                            if (num == 1) {
                                col = "primera columna";
                            } else if (num == 2) {
                                col = "segunda columna";
                            } else if (num == 3) {
                                col = "tercera columna";
                            } else {
                                throw new NumeroFueraDeRango();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            double monto = sc.nextDouble();
                            sc.nextLine();

                            ruleta.apostar(col, monto);
                            System.out.println("Apuesta realizada a " + col);
                        } catch (RuntimeException e) {
                            throw new RuntimeException();
                        } finally {
                            sc.nextLine();
                        }
                        break;

                    case 5:
                        System.out.println("Apuesta de mayor o menor:");
                        try {
                            System.out.println("Escriba si quiere apostar a menor (1 - 18) o mayor (19 - 36): ");
                            String menorMayor = sc.nextLine().toLowerCase();

                            if (!menorMayor.equals("menor") && (!menorMayor.equals("mayor"))) {
                                throw new DatoIncorrectoException();
                            }
                            System.out.println("Ingrese monto a apostar: ");
                            double monto = sc.nextDouble();
                            sc.nextLine();

                            ruleta.apostar(menorMayor, monto);
                            System.out.println("Apuesta realizada a " + menorMayor);
                        } catch (RuntimeException | DatoIncorrectoException e) {
                            throw new RuntimeException();
                        } finally {
                            sc.nextLine();
                        }
                        break;
                    case 6:
                        System.out.println("Apuesta de par o impar:");

                        try {
                            System.out.println("Escriba si quiere apostar a par o impar: ");
                            String parImpar = sc.nextLine().toLowerCase();

                            if (!parImpar.equals("par") && (!parImpar.equals("impar"))) {
                                throw new DatoIncorrectoException();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            double monto = sc.nextDouble();
                            sc.nextLine();

                            ruleta.apostar(parImpar, monto);
                            System.out.println("Apuesta realizada a " + parImpar);
                        } catch (RuntimeException e) {
                            throw new RuntimeException();
                        } catch (DatoIncorrectoException e) {
                            throw new RuntimeException(e);
                        } finally {
                            sc.nextLine();
                        }
                        break;
                    case 7:
                        ruleta.pagarFichas();

                    default:
                        System.out.println("Opcion invalida");
                }
            } while (opcion != 8);
        }
}



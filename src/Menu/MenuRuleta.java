package Menu;

import Exceptions.DatoIncorrectoException;
import Exceptions.NumeroFueraDeRangoException;
import Exceptions.SaldoInsuficienteException;
import Modelo.Juegos.Ruleta;
import Modelo.Usuarios.Cliente;

import java.util.Scanner;

public class MenuRuleta {

        public static Scanner sc = new Scanner(System.in);

        public static void Start(Cliente jugador) {

/// -------------------- Menu base -----------------------------------

            System.out.println("Bienvenido a Ruleta");


            menuInicial();
            int salir = 1;

            do {
                System.out.println("Ingrese un numero: ");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        Ruleta ruleta = new Ruleta();
                        ingresarApuestas(ruleta, jugador);
                        break;
                    case 0:
                        salir = 0;
                        break;
                }
            } while (salir == 1);

        }
        public static void menuInicial(){
            System.out.println("1. Jugar");
            System.out.println("0. Salir");
        }
    public static void mostrarSaldo(Cliente jugador) {
        System.out.println("----- Saldo actual: " + jugador.getSaldo());
    }


    /// -------------------- Menu apuestas -----------------------------------
        public static void ingresarApuestas(Ruleta ruleta, Cliente jugador) throws RuntimeException, SaldoInsuficienteException {
            //Menu con opciones

            int opcion = 0;

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

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta plenos: ");
                        try {
                            System.out.println("Ingrese numero al que quiera apostar (0 - 36): ");
                            String ingrNum =  sc.nextLine();
                            int num =  Integer.parseInt(ingrNum);

                            if (num < 0 || num > 36) {
                                throw new NumeroFueraDeRangoException();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            String ingrMonto =  sc.nextLine();
                            double monto =   Double.parseDouble(ingrMonto);

                            if(jugador.getSaldo() < monto){
                                throw new SaldoInsuficienteException();
                            }

                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(String.valueOf(num), monto);

                            System.out.println("Apuesta realizada a " + num);

                        } catch (NumberFormatException e) { //Excepcion en caso de que se ingrese un string
                            System.out.println("Error: debe ingresar un numero valido");
                        } catch (NumeroFueraDeRangoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta de color:");
                        try {
                            System.out.println("Ingrese un color para apostar (rojo/negro): ");
                            String color = sc.nextLine().toLowerCase(); //convierte el string ingresado en minusculas

                            if (!color.equals("rojo") && !color.equals("negro")) {
                                throw new DatoIncorrectoException();
                            }
                            System.out.println("Ingrese monto a apostar: ");
                            String  ingrMonto =  sc.nextLine();
                            double monto =   Double.parseDouble(ingrMonto);

                            if(jugador.getSaldo() < monto){
                                throw new SaldoInsuficienteException();
                            }

                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(color, monto);

                            System.out.println("Apuesta realizada a " + color);

                        } catch (NumberFormatException e){
                            System.out.println("Error: debe ingresar un numero valido");
                        }catch (DatoIncorrectoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta de docenas:");
                        try {
                            System.out.println("Ingrese la docena a la que quiera apostar (1 / 2 / 3): ");
                            String ingrDocena = sc.nextLine();
                            int num = Integer.parseInt(ingrDocena);

                            String doc;

                            if (num == 1) {
                                doc = "primera docena";
                            } else if (num == 2) {
                                doc = "segunda docena";
                            } else if (num == 3) {
                                doc = "tercera docena";
                            } else {
                                throw new NumeroFueraDeRangoException();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            String ingrMonto =  sc.nextLine();
                            double monto =   Double.parseDouble(ingrMonto);

                            if(jugador.getSaldo() < monto){
                                throw new SaldoInsuficienteException();
                            }
                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(doc, monto);

                            System.out.println("Apuesta realizada a " + doc);

                        } catch (NumberFormatException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                        }catch (NumeroFueraDeRangoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta de columnas:");
                        try {
                            System.out.println("Ingrese la columna a la que quiera apostar (1 / 2 / 3): ");
                            String ingrColumna = sc.nextLine();
                            int num = Integer.parseInt(ingrColumna);

                            String col;
                            if (num == 1) {
                                col = "primera columna";
                            } else if (num == 2) {
                                col = "segunda columna";
                            } else if (num == 3) {
                                col = "tercera columna";
                            } else {
                                throw new NumeroFueraDeRangoException();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            String ingrMonto =  sc.nextLine();
                            double monto =   Double.parseDouble(ingrMonto);

                            if(jugador.getSaldo() < monto){
                                throw new SaldoInsuficienteException();
                            }
                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(col, monto);

                            System.out.println("Apuesta realizada a " + col);

                        } catch (NumberFormatException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                        } catch (NumeroFueraDeRangoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta de mayor o menor:");
                        try {
                            System.out.println("Escriba si quiere apostar a MENOR (1 - 18) o MAYOR (19 - 36): ");
                            String menorMayor = sc.nextLine().toLowerCase();

                            if (!menorMayor.equals("menor") && (!menorMayor.equals("mayor"))) {
                                throw new DatoIncorrectoException();
                            }
                            System.out.println("Ingrese monto a apostar: ");
                            String ingrMonto =  sc.nextLine();
                            double monto =   Double.parseDouble(ingrMonto);

                            if(jugador.getSaldo() < monto){
                                throw new SaldoInsuficienteException();
                            }
                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(menorMayor, monto);

                            System.out.println("Apuesta realizada a " + menorMayor);

                        } catch (NumberFormatException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                        } catch (DatoIncorrectoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 6:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta de par o impar:");
                        try {
                            System.out.println("Escriba si quiere apostar a PAR o IMPAR: ");
                            String parImpar = sc.nextLine().toLowerCase();

                            if (!parImpar.equals("par") && (!parImpar.equals("impar"))) {
                                throw new DatoIncorrectoException();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            String ingrMonto =  sc.nextLine();
                            double monto =   Double.parseDouble(ingrMonto);

                            if(jugador.getSaldo() < monto){
                                throw new SaldoInsuficienteException();
                            }
                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(parImpar, monto);

                            System.out.println("Apuesta realizada a " + parImpar);

                        } catch (NumberFormatException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                        }catch (DatoIncorrectoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 7:
                        double ganancia = ruleta.pagarFichas();
                        jugador.setSaldo(jugador.getSaldo() + ganancia);
                        System.out.println("Numero ganador: " + ruleta.getNumeroSalidor());
                        System.out.println("Pago recibido: " + ganancia);
                        System.out.println("Nuevo saldo: " + jugador.getSaldo());

                        ruleta.limpiarApuestas();

                        break;
                    case 8:
                        System.out.println("Saliendo de la mesa");
                        ruleta.limpiarApuestas();
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            } while (opcion != 8);
        }
}



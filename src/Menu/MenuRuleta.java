package Menu;

import Enums.ENumerosRuleta;
import Exceptions.DatoIncorrectoException;
import Exceptions.NumeroFueraDeRangoException;
import Exceptions.SaldoInsuficienteException;
import Modelo.Juegos.Ruleta;
import Modelo.Usuarios.Cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuRuleta {

        public static Scanner sc = new Scanner(System.in);

        public static void Start(Cliente jugador) {

/// -------------------- Menu base -----------------------------------

            System.out.println("Bienvenido a Ruleta");


            menuInicial();
            int salir = 1;

            do {
                System.out.print("Ingrese una opcion: ");
                int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        Ruleta ruleta = new Ruleta();
                        ingresarApuestas(ruleta, jugador);
                        salir = 0;
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
        System.out.println("Jugador: " + jugador.getNombre() + " ---- Saldo: $" + jugador.getSaldo() + "\n");
    }
    public static void mostrarTablero() {
        System.out.println("\n");
        System.out.println("+---+-------------------------------------------------------------+---------+");
        System.out.println("|   | ( 3) ( 6) ( 9) (12) (15) (18) (21) (24) (27) (30) (33) (36) | 3ra Col |");
        System.out.println("| 0 | ( 2) ( 5) ( 8) (11) (14) (17) (20) (23) (26) (29) (32) (35) | 2da Col |");
        System.out.println("|   | ( 1) ( 4) ( 7) (10) (13) (16) (19) (22) (25) (28) (31) (34) | 1ra Col |");
        System.out.println("+---+-------------------------------------------------------------+---------+");
        System.out.println("|   1ra Doc. (1-12)   |  2da Doc. (13-24)   |   3ra Doc. (25-36)  |");
        System.out.println("+-------------------+-----------------+------------------------------------+");
        System.out.println("|   1 al 18 (MENOR) |   PAR   |  ROJO | NEGRO |  IMPAR  | 19 al 36 (MAYOR) |");
        System.out.println("+-------------------+---------+-------+-------+---------+------------------+");
        System.out.println("\n");
    }


    /// -------------------- Menu apuestas -----------------------------------
        public static void ingresarApuestas(Ruleta ruleta, Cliente jugador) throws RuntimeException, SaldoInsuficienteException {
            //Menu con opciones

            int opcion = 0;

            do {
                mostrarTablero();
                System.out.println("1. Jugar plenos ");
                System.out.println("2. Color ");
                System.out.println("3. Docenas ");
                System.out.println("4. Columnas ");
                System.out.println("5. MayorMenor ");
                System.out.println("6. ParImpar ");
                System.out.println("7. Girar ruleta");
                System.out.println("8. Salir");

                System.out.print("Ingrese la opcion a la que quiera apostar: ");

                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta plenos: ");
                        try {
                            System.out.println("Ingrese numero al que quiera apostar (0 - 36): ");
                            int num =  sc.nextInt();
                            sc.nextLine();

                            if (num < 0 || num > 36) { /// SE COMPRUEBA QUE EL NUMERO APOSTADO ESTÉ DENTRO DEL TABLERO
                                throw new NumeroFueraDeRangoException(); ///SI NO, LANZA EXCEPCION
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            double monto =  sc.nextDouble();
                            sc.nextLine();

                            if (monto <= 0) { /// EL MONTO APOSTADO NO PUEDE SER NEGATIVO
                                System.out.println("Error: El monto a apostar debe ser positivo.");
                                break;
                            }

                            if(jugador.getSaldo() < monto){ /// COMPRUEBA QUE EL JUGADOR TIENE MAS SALDO QUE EL QUE QUIERE APOSTAR
                                throw new SaldoInsuficienteException();
                            }

                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(String.valueOf(num), monto);

                            System.out.println("Apuesta realizada a " + num);

                        } catch (InputMismatchException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                            sc.nextLine();
                        } catch (NumeroFueraDeRangoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 2:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta de color:");
                        try {
                            System.out.println("Ingrese un color para apostar (rojo/negro): ");
                            String color = sc.nextLine().toLowerCase(); ///CONVIERTE EL STRING INGRESADO EN MINUSCULAS

                            if (!color.equals("rojo") && !color.equals("negro")) {
                                throw new DatoIncorrectoException(); ///SI EL STRING NO ES rojo o negro SE RECHAZA Y SE VUELVE A INGRESAR
                            }
                            System.out.println("Ingrese monto a apostar: ");
                            double monto =  sc.nextDouble();
                            sc.nextLine();

                            if (monto <= 0) { /// EL MONTO APOSTADO NO PUEDE SER NEGATIVO
                                System.out.println("Error: El monto a apostar debe ser positivo.");
                                break;
                            }

                            if(jugador.getSaldo() < monto){
                                throw new SaldoInsuficienteException();
                            }

                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(color, monto);

                            System.out.println("Apuesta realizada al color " + color);

                        } catch (InputMismatchException e){
                            System.out.println("Error: debe ingresar un numero valido");
                            sc.nextLine();
                        }catch (DatoIncorrectoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta de docenas:");
                        try {
                            System.out.println("Ingrese la docena a la que quiera apostar (1 / 2 / 3): ");
                            int num = sc.nextInt();
                            sc.nextLine();

                            String doc;

                            if (num == 1) { /// CON 1, 2 O 3 SE LE ASIGNA UNA DOCENA, SI SALE DE ESE RANGO, EXCEPCION
                                doc = "primera docena";
                            } else if (num == 2) {
                                doc = "segunda docena";
                            } else if (num == 3) {
                                doc = "tercera docena";
                            } else {
                                throw new NumeroFueraDeRangoException();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            double monto =  sc.nextDouble();
                            sc.nextLine();

                            if (monto <= 0) { /// EL MONTO APOSTADO NO PUEDE SER NEGATIVO
                                System.out.println("Error: El monto a apostar debe ser positivo.");
                                break;
                            }

                            if(jugador.getSaldo() < monto){ /// COMPRUEBA QUE EL JUGADOR TIENE MAS SALDO QUE EL QUE QUIERE APOSTAR
                                throw new SaldoInsuficienteException();
                            }
                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(doc, monto);

                            System.out.println("Apuesta realizada a " + doc + "era docena");

                        } catch (InputMismatchException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                            sc.nextLine();
                        }catch (NumeroFueraDeRangoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 4:
                        mostrarSaldo(jugador);
                        System.out.println("Apuesta de columnas:");
                        try {
                            System.out.println("Ingrese la columna a la que quiera apostar (1 / 2 / 3): ");
                            int num = sc.nextInt();
                            sc.nextLine();

                            String col;
                            if (num == 1) { /// CON 1, 2 O 3 SE LE ASIGNA UNA COLUMNA, SI SALE DE ESE RANGO, EXCEPCION
                                col = "primera columna";
                            } else if (num == 2) {
                                col = "segunda columna";
                            } else if (num == 3) {
                                col = "tercera columna";
                            } else {
                                throw new NumeroFueraDeRangoException();
                            }

                            System.out.println("Ingrese monto a apostar: ");
                            double  monto =  sc.nextDouble();
                            sc.nextLine();

                            if (monto <= 0) { /// EL MONTO APOSTADO NO PUEDE SER NEGATIVO
                                System.out.println("Error: El monto a apostar debe ser positivo.");
                                break;
                            }

                            if(jugador.getSaldo() < monto){ /// COMPRUEBA QUE EL JUGADOR TIENE MAS SALDO QUE EL QUE QUIERE APOSTAR
                                throw new SaldoInsuficienteException();
                            }
                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(col, monto);

                            System.out.println("Apuesta realizada a " + col + "era columna");

                        } catch (InputMismatchException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                            sc.nextLine();
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
                            double monto =   sc.nextDouble();
                            sc.nextLine();

                            if (monto <= 0) { /// EL MONTO APOSTADO NO PUEDE SER NEGATIVO
                                System.out.println("Error: El monto a apostar debe ser positivo.");
                                break;
                            }

                            if(jugador.getSaldo() < monto){ /// COMPRUEBA QUE EL JUGADOR TIENE MAS SALDO QUE EL QUE QUIERE APOSTAR
                                throw new SaldoInsuficienteException();
                            }
                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(menorMayor, monto);

                            System.out.println("Apuesta realizada a " + menorMayor);

                        } catch (InputMismatchException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                            sc.nextLine();
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
                            double monto = sc.nextDouble();
                            sc.nextLine();

                            if (monto <= 0) { /// EL MONTO APOSTADO NO PUEDE SER NEGATIVO
                                System.out.println("Error: El monto a apostar debe ser positivo.");
                                break;
                            }

                            if(jugador.getSaldo() < monto){ /// COMPRUEBA QUE EL JUGADOR TIENE MAS SALDO QUE EL QUE QUIERE APOSTAR
                                throw new SaldoInsuficienteException();
                            }
                            jugador.setSaldo(jugador.getSaldo() - monto);

                            ruleta.apostar(parImpar, monto);

                            System.out.println("Apuesta realizada a " + parImpar);

                        } catch (InputMismatchException e) {
                            System.out.println("Error: debe ingresar un numero valido");
                            sc.nextLine();
                        }catch (DatoIncorrectoException | SaldoInsuficienteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 7:
                        double ganancia = ruleta.pagarFichas(); ///SE LLAMA AL METODO QUE DEVUELVE LA GANANCIA
                        jugador.setSaldo(jugador.getSaldo() + ganancia); ///SE LE SUMA A LA CUENTA DEL JUGADOR

                        int numeroGanador = ruleta.getNumeroSalidor(); ///NUMERO QUE SALIÓ
                        String color = ENumerosRuleta.getColorMayuscula(numeroGanador); ///COLOR DEL NUMERO QUE SALIO

                        System.out.println("Numero ganador: " + numeroGanador + " - " + color);
                        System.out.println("Pago recibido: " + ganancia);
                        System.out.println("Nuevo saldo: " + jugador.getSaldo());

                        GestionMenu.guardadoAutomatico();
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



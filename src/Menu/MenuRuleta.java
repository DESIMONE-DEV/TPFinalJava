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
    public static final String R = "\u001B[31m"; // ROJO
    public static final String G = "\u001B[32m"; // VERDE
    public static final String B = "\u001B[90m"; // NEGRO
    public static final String Z = "\u001B[0m"; // RESET


    public static Scanner sc = new Scanner(System.in);
    public static double sumaApuesta =0;
    /// -------------------- MENU BASE RULETA -------------------------------///
    public static void Start(Cliente jugador) {

            System.out.println("Bienvenido a Ruleta");
            int salir = 1;

            do {
                menuInicial();

                System.out.print("Ingrese una opcion: ");
                int opcion = sc.nextInt();
                sc.nextLine();
                switch (opcion) {
                    case 1:
                        Ruleta ruleta = new Ruleta();
                        ingresarApuestas(ruleta, jugador);
                        salir = 0;
                        break;
                    case 2:
                        comojugar();
                        break;
                    case 0:
                        salir = 0;
                        break;
                }
            } while (salir == 1);

        }
    /// --------------------FIN MENU BASE RULETA -------------------------------///
    ///
    /// -------------------- OPCIONES MENU INICIAL -------------------------------///
    ///
    public static void menuInicial(){
            System.out.println("1. Jugar");
            System.out.println("2. Como Jugar");
            System.out.println("0. Salir");
        }
    /// -------------------- FIN MENU INICIAL -------------------------------///
    ///
    /// -------------------- MUESTRA DE SALDO-------------------------------///

    public static void mostrarSaldo(Cliente jugador) {
        System.out.println("Jugador: " + jugador.getNombre() + " ---- Saldo: $" + jugador.getSaldo() + "\n");
    }
    /// -------------------- FIN MUESTRA DE SALDO -------------------------------///
    ///
    /// -------------------- MUESTRA DE TABLERO -------------------------------///
    public static void mostrarTablero() {
        System.out.println("\n");
        System.out.println("+---+-------------------------------------------------------------+---------+");
        System.out.println("|   | ("+R+" 3"+Z+") ("+B+" 6"+Z+") ("+R+" 9"+Z+") ("+R+"12"+Z+") ("+B+"15"+Z+") ("+R+"18"+Z+") ("+R+"21"+Z+") ("+B+"24"+Z+") ("+R+"27"+Z+") ("+R+"30"+Z+") ("+B+"33"+Z+") ("+R+"36"+Z+") | 3ra Col |");
        System.out.println("| "+G+"0"+Z+" | ("+B+" 2"+Z+") ("+R+" 5"+Z+") ("+B+" 8"+Z+") ("+B+"11"+Z+") ("+R+"14"+Z+") ("+B+"17"+Z+") ("+B+"20"+Z+") ("+R+"23"+Z+") ("+B+"26"+Z+") ("+B+"29"+Z+") ("+R+"32"+Z+") ("+B+"35"+Z+") | 2da Col |");
        System.out.println("|   | ("+R+" 1"+Z+") ("+B+" 4"+Z+") ("+R+" 7"+Z+") ("+B+"10"+Z+") ("+B+"13"+Z+") ("+R+"16"+Z+") ("+R+"19"+Z+") ("+B+"22"+Z+") ("+R+"25"+Z+") ("+B+"28"+Z+") ("+B+"31"+Z+") ("+R+"34"+Z+") | 1ra Col |");
        System.out.println("+---+-------------------------------------------------------------+---------+");
        System.out.println("|   1ra Doc. (1-12)   |  2da Doc. (13-24)   |   3ra Doc. (25-36)  |");
        System.out.println("+-------------------+-----------------+------------------------------------+");
        System.out.println("|   1 al 18 (MENOR) |   PAR   | "+R+" ROJO "+Z+"|"+B+" NEGRO "+Z+"|  IMPAR  | 19 al 36 (MAYOR) |");
        System.out.println("+-------------------+---------+-------+-------+---------+------------------+");
        System.out.println("\n");
    }

    /// -------------------- FIN MUESTRA TABLERO -------------------------------///
    ///
    ///
    /// -------------------- MENU BASE DE RULETA -------------------------------///
    public static void ingresarApuestas(Ruleta ruleta, Cliente jugador) throws RuntimeException, SaldoInsuficienteException {

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

                            sumaApuesta += monto;  /// Acumula el saldo gastado para guardad en el stat

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

                            sumaApuesta += monto;

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

                            sumaApuesta += monto;

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

                            sumaApuesta += monto;

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

                            sumaApuesta += monto;

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

                            sumaApuesta += monto;

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

                        if(sumaApuesta > 0){
                             GestionMenu.crearStats(jugador.getDni(),"Jugada Ruleta",ganancia-sumaApuesta);
                        }

                        GestionMenu.guardadoAutomatico();
                        ruleta.limpiarApuestas();
                        sumaApuesta = 0;

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

    /// -------------------- FIN MENU BASE DE RULETA -------------------------------///
    ///
    /// -------------------- INFO DE JUEGO -------------------------------///

    public static void comojugar(){
        System.out.println("\n\nEn cada partida ,después de que la bola gire varias vueltas caerá sobre una de las casillas de la ruleta.\n" +
                "El objetivo del juego es predecir en que casilla caerá la bola.\n" +
                "Después de cerrar las apuestas, se anunciará el numero ganador y las apuestas ganadoras,\n" +
                "seguidamente se procederá a retirar las apuestas perdedoras y a pagar las apuestas ganadoras.\n" +
                "\n" +
                "Apuesta      Pago + apuesta\n" +
                "Rojo/Negro     1x  +    1x   \n" +
                "Par/Impar      1x  +    1x   \n" +
                "Menor/Mayor    1x  +    1x   \n" +
                "Docena         2x  +    1x   \n" +
                "Columna        2x  +    1x   \n" +
                "Pleno         35x  +    1x   \n" +
                "Siendo x lo apostado \n\n");


    }
    /// -------------------- FIN DE INFO DE JUEGO -------------------------------///
}



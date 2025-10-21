package Menu;

import Exceptions.CantidadPosicionesInvalidaException;
import Exceptions.SaldoInsuficienteException;
import Modelo.Juegos.TriPoker;
import Modelo.Usuarios.Cliente;
import java.util.Scanner;

public class MenuTriPoker {

    public static Scanner scan = new Scanner(System.in);

    public static void start(Cliente jugador) {

        TriPoker juego = new TriPoker();
        int opcion = 777, salir = 1, asientos = 0;
        System.out.println("----- Bienvenido a TriPoker -----");

        do {
            menuInicial();
            try {
                opcion = scan.nextInt();
            } catch (RuntimeException e) {
                System.out.println("Ingrese un numero");
            } finally {
                scan.nextLine();
            }

            switch (opcion) {
                case 0:
                    salir = 0;
                    break;

                case 1:
                    try {
                        System.out.println("En cuantos asientos va a jugar?");
                        asientos = scan.nextInt();
                        scan.nextLine();

                        if(asientos < 1 || asientos > 3){
                            throw new CantidadPosicionesInvalidaException();
                        }

                    } catch (CantidadPosicionesInvalidaException e) {
                        System.out.println(e.getMessage());
                        scan.nextLine();
                        break;
                    } catch (RuntimeException e) {
                        System.out.println("Debe ingresar un numero entre 1 y 3");
                        scan.nextLine();
                        break;
                    }
                    ingresarApuestas(asientos, juego, jugador);

                    /// FALTA TERMINAR DESDE ACAAAAAA
                    break;

                default:
                    System.out.println("Opcion invalida intente nuevamente");
                    break;
            }
        }while (salir == 1) ;
    }

    public static void menuInicial(){
        System.out.println("1- Jugar");
        System.out.println("0- Salir");
        System.out.print("Su opcion: ");
    }

    public static void ingresarApuestas(int cantAsientos, TriPoker juego, Cliente jugador) throws RuntimeException, SaldoInsuficienteException{

        double aux;

        if(cantAsientos >= 1){
            try{
            System.out.print("Ingrese su apuesta en el Bonus 1: ");
            aux = scan.nextDouble();
            scan.nextLine();
            if(jugador.getSaldo() >= aux) {
                juego.getMano1().setBonus(aux);
                jugador.setSaldo(jugador.getSaldo() - aux);
            } else{
                throw new SaldoInsuficienteException();
            }

            System.out.print("Ingrese su apuesta en el Ante 1: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano1().setBonus(aux);
                    jugador.setSaldo(jugador.getSaldo() - aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

            } catch (RuntimeException e) {
                scan.nextLine();
                throw new RuntimeException(e);
            }
        }

        if(cantAsientos >=2){
            try{
                System.out.print("Ingrese su apuesta en el Bonus 2: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano2().setBonus(aux);
                    jugador.setSaldo(jugador.getSaldo() - aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

                System.out.print("Ingrese su apuesta en el Ante 2: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano2().setBonus(aux);
                    jugador.setSaldo(jugador.getSaldo() - aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

            } catch (RuntimeException e) {
                scan.nextLine();
                throw new RuntimeException(e);
            }
        }

        if(cantAsientos >=3){
            try{
                System.out.print("Ingrese su apuesta en el Bonus 3: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano3().setBonus(aux);
                    jugador.setSaldo(jugador.getSaldo() - aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

                System.out.print("Ingrese su apuesta en el Ante 3: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano3().setBonus(aux);
                    jugador.setSaldo(jugador.getSaldo() - aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

            } catch (RuntimeException e) {
                scan.nextLine();
                throw new RuntimeException(e);
            }
        }
    }
}

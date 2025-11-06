package Menu;

import Exceptions.CantidadPosicionesInvalidaException;
import Exceptions.MazoVacioException;
import Exceptions.SaldoInsuficienteException;
import Modelo.Juegos.TriPoker;
import Modelo.Usuarios.Cliente;
import java.util.Scanner;

public class MenuTriPoker {

    public static Scanner scan = new Scanner(System.in);

    public static void start(Cliente jugador) {

        TriPoker juego = new TriPoker();
        int opcion = 777, salir = 1, asientos = 0;
        double bonus, bet;

        System.out.println("----- Bienvenido a TriPoker -----");

        do {
            try {
                menuInicial();
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
                        break;
                    } catch (RuntimeException e) {
                        System.out.println("Debe ingresar un numero entre 1 y 3");
                        scan.nextLine();
                        break;
                    }
                    ingresarApuestas(asientos, juego, jugador);

                    try {
                        juego.repartir(asientos);
                    }catch (MazoVacioException e){
                        System.out.println(e.getMessage());
                    }

                    limpiarPantalla();      /// MUESTRO BANCA Y MANOS
                    System.out.println(juego.listarBancaVacia());
                    System.out.println(juego.listarManos(asientos));

                    apuestaBet(asientos, juego); /// PREGUNTO SI SIGUE APOSTANDO

                    limpiarPantalla();  /// MUESTRO BANCA CON JUEGO Y MANOS
                    System.out.println(juego.listarBancaConJuego());
                    System.out.println(juego.listarManos(asientos));

                    mostrarPagoBonus(juego, asientos);
                    mostrarPagoBet(juego, asientos);
                    jugador.cargarSaldo(juego.pagarFichas());

                    enterContinue();
                    limpiarPantalla();

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

    public static void apuestaBet(int asientos, TriPoker juego){
        char yesNo;
        if(asientos >= 1){
            System.out.print("Apuesta en mano 1? (s/n): ");
            yesNo =  scan.nextLine().toLowerCase().charAt(0);
            if(yesNo == 's'){
                juego.asignarBetMano1();
            }else{
                juego.removerApuestaMano1();
            }
        }
        if(asientos >= 2){
            System.out.print("Apuesta en mano 2? (s/n): ");
            yesNo =  scan.nextLine().toLowerCase().charAt(0);
            if(yesNo == 's'){
                juego.asignarBetMano2();
            }else{
                juego.removerApuestaMano2();
            }
        }
        if(asientos >= 3){
            System.out.print("Apuesta en mano 3? (s/n): ");
            yesNo =  scan.nextLine().toLowerCase().charAt(0);
            if(yesNo == 's'){
                juego.asignarBetMano3();
            }else{
                juego.removerApuestaMano3();
            }
        }
    }

    public static void mostrarPagoBonus(TriPoker juego, int asientos){
        double bonus;
        if(asientos >=1) {
            System.out.println("\nMano 1:");
            if (juego.pagarBonus(juego.getMano1()) > 0) {
                bonus = juego.pagarBonus(juego.getMano1());
                System.out.println("GANA BONUS: $" + bonus);
            } else {
                System.out.println("PIERDE BONUS");
            }
        }
        if(asientos >=2) {
            System.out.println("\nMano 2:");
            if (juego.pagarBonus(juego.getMano2()) > 0) {
                bonus = juego.pagarBonus(juego.getMano2());
                System.out.println("GANA BONUS: $" + bonus);
            } else {
                System.out.println("PIERDE BONUS");
            }
        }
        if(asientos >=3) {
            System.out.println("\nMano 3:");
            if (juego.pagarBonus(juego.getMano3()) > 0) {
                bonus = juego.pagarBonus(juego.getMano3());
                System.out.println("GANA BONUS: $" + bonus);
            } else {
                System.out.println("PIERDE BONUS");
            }
        }
    }

    public static void mostrarPagoBet(TriPoker juego, int asientos){
        double bet;
        if(asientos >=1) {
            System.out.println("\nMano 1:");
            if (juego.jugadorGana(juego.getMano1()) > 0) {
                bet = juego.pagarBet(juego.getMano1());
                System.out.println("GANA BET: $" + bet);
                System.out.println("GANA ANTE: $" + bet);

            } else if(juego.jugadorGana(juego.getMano1()) < 0){
                System.out.println("PIERDE BET");
            }else{
                System.out.println("HAY EMPATE, RECUPERA SU APUESTA");
            }
        }
        if(asientos >=2) {
            System.out.println("\nMano 2:");
            if (juego.jugadorGana(juego.getMano2()) > 0) {
                bet = juego.pagarBet(juego.getMano2());
                System.out.println("GANA BET: $" + bet);
                System.out.println("GANA ANTE: $" + bet);

            } else if(juego.jugadorGana(juego.getMano2()) < 0){
                System.out.println("PIERDE BET");
            }else{
                System.out.println("HAY EMPATE, RECUPERA SU APUESTA");
            }
        }
        if(asientos >=3) {
            System.out.println("\nMano 3:");
            if (juego.jugadorGana(juego.getMano3()) > 0) {
                bet = juego.pagarBet(juego.getMano3());
                System.out.println("GANA BET: $" + bet);
                System.out.println("GANA ANTE: $" + bet);

            } else if(juego.jugadorGana(juego.getMano3()) < 0){
                System.out.println("PIERDE BET");
            }else{
                System.out.println("HAY EMPATE, RECUPERA SU APUESTA");
            }
        }
    }

    public static void limpiarPantalla(){
        for(int i = 0; i<25; i++){
            System.out.println(" ");
        }
    }

    public static void enterContinue(){
        System.out.println("Presione enter para continuar...");
        scan.nextLine();
    }
}

package Menu;

import Exceptions.CantidadPosicionesInvalidaException;
import Exceptions.MazoVacioException;
import Exceptions.SaldoInsuficienteException;
import Modelo.Juegos.TriPoker;
import Modelo.Usuarios.Cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuTriPoker {

    public static Scanner scan = new Scanner(System.in);

    public static void start(Cliente jugador) {

        TriPoker juego = new TriPoker();
        int opcion = 777, salir = 1, asientos = 0;

        System.out.println("----- Bienvenido a TriPoker -----");
        System.out.println("Hola " + jugador.getNombre());

        do {            /// COMIENZO DE MENU JUEGO
            try {
                System.out.println("Jugador: " + jugador.getNombre() + " ---- Saldo: $" + jugador.getSaldo() + "\n");
                menuInicial();
                opcion = scan.nextInt();
            }catch(InputMismatchException e){
                System.out.println("No sea  malo ingrese un numero");
            } finally {
                scan.nextLine();
                limpiarPantalla();
            }

            switch (opcion) {
                case 0:
                    salir = 0;
                    break;

                case 1:
                    try {
                        System.out.println("Jugador: " + jugador.getNombre() + " ---- Saldo: $" + jugador.getSaldo() + "\n");
                        System.out.println("En cuantos asientos va a jugar?");
                        asientos = scan.nextInt();
                        scan.nextLine();                /// PREGUNTAMOS CUANTOS ASIENTOS, SI NO ES UN NUMERO CORRECTO
                                                        /// LANZA EXCEPCION

                        if(asientos < 1 || asientos > 3){
                            throw new CantidadPosicionesInvalidaException();
                        }

                    } catch (CantidadPosicionesInvalidaException e) {
                        System.out.println(e.getMessage());
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Debe ingresar un numero entre 1 y 3");
                        scan.nextLine();
                        break;
                    }
                    try {                                       /// INGRESAMOS APUESTAS DE BONUS Y ANTE
                        ingresarApuestas(asientos, juego, jugador); /// LANZA EXCEPCION SI SE QUEDA SIN SALDO Y
                                                                    /// LO DEVOLVEMOS AL INICIO
                    } catch (SaldoInsuficienteException e) {
                        System.out.println("No posee suficiente saldo para ingresar esa apuesta");
                        System.out.println("Sus apuestas seran devueltas");
                        jugador.cargarSaldo(juego.getMano1().getAnte() + juego.getMano1().getBonus());
                        jugador.cargarSaldo(juego.getMano2().getAnte() + juego.getMano2().getBonus());
                        jugador.cargarSaldo(juego.getMano3().getAnte() + juego.getMano3().getBonus());
                        break;

                    }
                    try {                       /// SE REPARTEN LAS CARTAS
                        juego.repartir();
                    }catch (MazoVacioException e){
                        System.out.println(e.getMessage());
                    }

                    limpiarPantalla();      /// MUESTRO BANCA Y MANOS
                    System.out.println(juego.listarBancaVacia());
                    System.out.println("\nJugador: " + jugador.getNombre() + " ---- Saldo: $" + jugador.getSaldo());
                    System.out.println(juego.listarManos(asientos));

                    apuestaBet(asientos, juego, jugador); /// PREGUNTO SI SIGUE APOSTANDO

                    limpiarPantalla();  /// MUESTRO BANCA CON JUEGO Y MANOS
                    System.out.println(juego.listarBancaConJuego());
                    System.out.println(juego.listarManos(asientos));

                    mostrarPagos(juego, asientos);      /// SE REALIZAN LOS PAGOS Y SE MUESTRA CUANTO GANO
                    jugador.cargarSaldo(juego.pagarFichas());
                    System.out.println("\nJugador: " + jugador.getNombre() + " ---- Saldo: $" + jugador.getSaldo() + "\n");

                    enterContinue();
                    juego.recuperarMazo();
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

    public static void ingresarApuestas(int cantAsientos, TriPoker juego, Cliente jugador) throws SaldoInsuficienteException{

        double aux;

        if(cantAsientos >= 1){
            try{
                System.out.print("Ingrese su apuesta en el Bonus 1: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano1().setBonus(aux);
                    jugador.retirarSaldo(aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

                System.out.print("Ingrese su apuesta en el Ante 1: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano1().setAnte(aux);
                    jugador.retirarSaldo(aux);
                } else{
                    throw new SaldoInsuficienteException();
                }
            }catch(InputMismatchException e){
                System.out.println("No sea  malo ingrese un numero");
                scan.nextLine();
                ingresarApuestas(cantAsientos, juego, jugador);
            }
        }

        if(cantAsientos >=2){
            try{
                System.out.print("Ingrese su apuesta en el Bonus 2: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano2().setBonus(aux);
                    jugador.retirarSaldo(aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

                System.out.print("Ingrese su apuesta en el Ante 2: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano2().setAnte(aux);
                    jugador.retirarSaldo(aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

            }catch(InputMismatchException e){
                System.out.println("No sea  malo ingrese un numero");
                ingresarApuestas(cantAsientos, juego, jugador);
                scan.nextLine();
            }
        }

        if(cantAsientos >=3){
            try{
                System.out.print("Ingrese su apuesta en el Bonus 3: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano3().setBonus(aux);
                    jugador.retirarSaldo(aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

                System.out.print("Ingrese su apuesta en el Ante 3: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(jugador.getSaldo() >= aux) {
                    juego.getMano3().setAnte(aux);
                    jugador.retirarSaldo(aux);
                } else{
                    throw new SaldoInsuficienteException();
                }

            }catch(InputMismatchException e){
                System.out.println("No sea  malo ingrese un numero");
                scan.nextLine();
                ingresarApuestas(cantAsientos, juego, jugador);
            }
        }
    }

    public static void apuestaBet(int asientos, TriPoker juego, Cliente jugador){
        String yesNo;
        if(asientos >= 1){
            System.out.print("Apuesta en mano 1? (s/n): ");
            yesNo =  scan.nextLine().toLowerCase();
            if(yesNo == "s") {
                if (jugador.getSaldo() >= juego.getMano1().getAnte()) {
                    jugador.retirarSaldo(juego.getMano1().getAnte());
                    juego.asignarBetMano1();
                } else {
                    System.out.println("No posee suficiente saldo, por esta mano se le permite jugar, luego debe cargar saldo");
                }
            }else{
                System.out.println("Apuestas removidas");
                juego.removerApuestaMano1();
                enterContinue();
            }
        }
        if(asientos >= 2){
            System.out.print("Apuesta en mano 2? (s/n): ");
            yesNo =  scan.nextLine().toLowerCase();
            if(yesNo == "s"){
                if(jugador.getSaldo() >= juego.getMano2().getAnte()){
                    jugador.retirarSaldo(juego.getMano2().getAnte());
                    juego.asignarBetMano2();
                }else{
                    System.out.println("No posee suficiente saldo, por esta mano se le permite jugar, luego debe cargar saldo");
                }
            }else{
                System.out.println("Apuestas removidas");
                juego.removerApuestaMano2();
                enterContinue();
            }
        }
        if(asientos >= 3){
            System.out.print("Apuesta en mano 3? (s/n): ");
            yesNo =  scan.nextLine().toLowerCase();
            if(yesNo == "s"){
                if(jugador.getSaldo() >= juego.getMano3().getAnte()){
                    jugador.retirarSaldo(juego.getMano3().getAnte());
                    juego.asignarBetMano3();
                }else{
                    System.out.println("No posee suficiente saldo, por esta mano se le permite jugar, luego debe cargar saldo");
                }
            }else{
                System.out.println("Apuestas removidas");
                juego.removerApuestaMano3();
                enterContinue();
            }
        }
    }

    public static void mostrarPagos(TriPoker juego, int asientos){
        double bonus, bet;
        if(asientos >=1) {
            System.out.println("\nMano 1:");
            if (juego.pagarBonus(juego.getMano1()) > 0) {
                bonus = juego.pagarBonus(juego.getMano1());
                System.out.println("GANA BONUS: $" + bonus);
            } else {
                System.out.println("PIERDE BONUS");
            }
            if (juego.jugadorGana(juego.getMano1()) > 0) {
                bet = juego.pagarBet(juego.getMano1());
                System.out.println("GANA ANTE: $" + bet);
                System.out.println("GANA BET: $" + bet);
            } else if(juego.jugadorGana(juego.getMano1()) < 0){
                System.out.println("PIERDE ANTE");
                System.out.println("PIERDE BET");
            }else{
                System.out.println("ANTE Y BET: HAY EMPATE, RECUPERA SU APUESTA");
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
            if (juego.jugadorGana(juego.getMano2()) > 0) {
                bet = juego.pagarBet(juego.getMano2());
                System.out.println("GANA ANTE: $" + bet);
                System.out.println("GANA BET: $" + bet);

            } else if(juego.jugadorGana(juego.getMano2()) < 0){
                System.out.println("PIERDE ANTE");
                System.out.println("PIERDE BET");
            }else{
                System.out.println("ANTE Y BET: HAY EMPATE, RECUPERA SU APUESTA");
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
            if (juego.jugadorGana(juego.getMano3()) > 0) {
                bet = juego.pagarBet(juego.getMano3());
                System.out.println("GANA ANTE: $" + bet);
                System.out.println("GANA BET: $" + bet);

            } else if(juego.jugadorGana(juego.getMano3()) < 0){
                System.out.println("PIERDE ANTE");
                System.out.println("PIERDE BET");
            }else{
                System.out.println("ANTE Y BET: HAY EMPATE, RECUPERA SU APUESTA");
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

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
        int opcion = 777, salir = 1, asientos, flag = 0;
        double pago;

        System.out.println("----- Bienvenido a TriPoker -----");
        System.out.println("Hola " + jugador.getNombre());

        do {            /// COMIENZO DE MENU JUEGO
            try {
                System.out.println("Jugador: " + jugador.getNombre() + " ---- Saldo: $" + jugador.getSaldo() + "\n");
                menuInicial();
                opcion = scan.nextInt();
            }catch(InputMismatchException e){
                System.out.println("No sea malo, ingrese un numero");
            } finally {
                scan.nextLine();
                limpiarPantalla();
            }

            switch (opcion) {
                case 0:
                    salir = 0;
                    break;

                case 2:
                    comoJugar();
                    enterContinue();
                    limpiarPantalla();
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


                    /// INGRESAMOS APUESTAS DE BONUS Y ANTE
                    do {
                        try {
                            if(ingresarApuestas(asientos, juego, jugador))
                                flag=1;                /// SI DEVUELVE TRUE, FLAG = 1 Y SALE DEL DO-WHILE
                                                    /// LANZA EXCEPCION SI SE QUEDA SIN SALDO Y
                                                    /// LO DEVOLVEMOS AL INICIO
                        } catch (SaldoInsuficienteException e) {
                            System.out.println("No posee suficiente saldo para ingresar esa apuesta");
                            System.out.println("Sus apuestas seran devueltas");
                            jugador.cargarSaldo(juego.getMano1().getAnte() + juego.getMano1().getBonus());
                            jugador.cargarSaldo(juego.getMano2().getAnte() + juego.getMano2().getBonus());
                            jugador.cargarSaldo(juego.getMano3().getAnte() + juego.getMano3().getBonus());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("No sea malo, ingrese un numero");
                            scan.nextLine();
                        }
                    }while(flag == 0);
                    flag=0;


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
                    pago = juego.pagarFichas();
                    jugador.cargarSaldo(pago);
                    GestionMenu.guardadoAutomatico();

                        /// CREO LAS STATS DE LA JUGADA QUE SE REALIZO
                    if(pago > 0){
                        GestionMenu.crearStats(jugador.getDni(), "Jugada TriPoker", pago - sumaApostado(juego) );
                    }else{
                        GestionMenu.crearStats(jugador.getDni(), "Jugada TriPoker", (-1)* sumaApostado(juego));
                    }
                    System.out.println("\nJugador: " + jugador.getNombre() + " ---- Saldo: $" + jugador.getSaldo() + "\n");

                    enterContinue();
                    juego.recuperarMazo();  /// SE REINICIA EL JUEGO PARA VOLVER A COMENZAR
                    limpiarPantalla();

                    break;

                default:
                    System.out.println("Opcion invalida, intente nuevamente");
                    break;
            }
        }while (salir == 1) ;
    }

    public static void menuInicial(){
        System.out.println("1- Jugar");
        System.out.println("2- Como Jugar?");
        System.out.println("0- Salir");
        System.out.print("Su opcion: ");
    }

    public static boolean ingresarApuestas(int cantAsientos, TriPoker juego, Cliente jugador) throws SaldoInsuficienteException{

        double aux;

        if(cantAsientos >= 1){
            do {
            System.out.print("Ingrese su apuesta en el Bonus 1: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(aux<0){
                    System.out.println("Ingrese un numero positivo por favor!");
                }
            }while(aux < 0);        /// PEDIMOS APUESTA POSITIVA SIEMPRE

            if(jugador.getSaldo() >= aux) {
                juego.getMano1().setBonus(aux);
                jugador.retirarSaldo(aux);
            } else{
                throw new SaldoInsuficienteException();
            }

            do{
                System.out.print("Ingrese su apuesta en el Ante 1: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(aux<0){
                    System.out.println("Ingrese un numero positivo por favor!");
                }
            }while(aux < 0);

            if(jugador.getSaldo() >= aux) {
                juego.getMano1().setAnte(aux);
                jugador.retirarSaldo(aux);
            } else{
                throw new SaldoInsuficienteException();
            }
        }

        if(cantAsientos >=2){
            do{
                System.out.print("Ingrese su apuesta en el Bonus 2: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(aux<0){
                    System.out.println("Ingrese un numero positivo por favor!");
                }
            }while(aux < 0);

            if(jugador.getSaldo() >= aux) {
                juego.getMano2().setBonus(aux);
                jugador.retirarSaldo(aux);
            } else{
                throw new SaldoInsuficienteException();
            }

            do{
                System.out.print("Ingrese su apuesta en el Ante 2: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(aux<0){
                    System.out.println("Ingrese un numero positivo por favor!");
                }
            }while(aux < 0);

            if(jugador.getSaldo() >= aux) {
                juego.getMano2().setAnte(aux);
                jugador.retirarSaldo(aux);
            } else{
                throw new SaldoInsuficienteException();
            }
        }

        if(cantAsientos >=3){
            do{
                System.out.print("Ingrese su apuesta en el Bonus 3: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(aux<0){
                    System.out.println("Ingrese un numero positivo por favor!");
                }
            }while(aux < 0);

            if(jugador.getSaldo() >= aux) {
                juego.getMano3().setBonus(aux);
                jugador.retirarSaldo(aux);
            } else{
                throw new SaldoInsuficienteException();
            }

            do{
                System.out.print("Ingrese su apuesta en el Ante 3: ");
                aux = scan.nextDouble();
                scan.nextLine();
                if(aux<0){
                    System.out.println("Ingrese un numero positivo por favor!");
                }
            }while(aux < 0);

            if(jugador.getSaldo() >= aux) {
                juego.getMano3().setAnte(aux);
                jugador.retirarSaldo(aux);
            } else{
                throw new SaldoInsuficienteException();
            }
        }
        return true;
    }

    public static void apuestaBet(int asientos, TriPoker juego, Cliente jugador){
        String yesNo;
        if(asientos >= 1){
            do {
                System.out.print("Apuesta en mano 1? (s/n): ");
                yesNo = scan.nextLine().toLowerCase();
                if (yesNo.equals("s")) {
                    if (jugador.getSaldo() >= juego.getMano1().getAnte()) {
                        jugador.retirarSaldo(juego.getMano1().getAnte());
                        juego.asignarBetMano1();
                    } else {
                        System.out.println("No posee suficiente saldo, por esta mano se le permite jugar, luego debe cargar saldo");
                    }
                } else if(yesNo.equals("n")){
                    System.out.println("Apuestas removidas");
                    juego.removerApuestaMano1();
                    enterContinue();
                }else{
                    System.out.println("Ingrese 's' o 'n' por favor");
                    enterContinue();
                }
            }while(!yesNo.equals("s") && !yesNo.equals("n"));
        }
        if(asientos >= 2){
            do {
                System.out.print("Apuesta en mano 2? (s/n): ");
                yesNo = scan.nextLine().toLowerCase();
                if (yesNo.equals("s")) {
                    if (jugador.getSaldo() >= juego.getMano2().getAnte()) {
                        jugador.retirarSaldo(juego.getMano2().getAnte());
                        juego.asignarBetMano2();
                    } else {
                        System.out.println("No posee suficiente saldo, por esta mano se le permite jugar, luego debe cargar saldo");
                    }
                } else if(yesNo.equals("n")){
                    System.out.println("Apuestas removidas");
                    juego.removerApuestaMano1();
                    enterContinue();
                }else{
                    System.out.println("Ingrese 's' o 'n' por favor");
                    enterContinue();
                }
            }while(!yesNo.equals("s") && !yesNo.equals("n"));
        }
        if(asientos >= 3){
            do {
                System.out.print("Apuesta en mano 3? (s/n): ");
                yesNo = scan.nextLine().toLowerCase();
                if (yesNo.equals("s")) {
                    if (jugador.getSaldo() >= juego.getMano3().getAnte()) {
                        jugador.retirarSaldo(juego.getMano3().getAnte());
                        juego.asignarBetMano3();
                    } else {
                        System.out.println("No posee suficiente saldo, por esta mano se le permite jugar, luego debe cargar saldo");
                    }
                } else if(yesNo.equals("n")){
                    System.out.println("Apuestas removidas");
                    juego.removerApuestaMano1();
                    enterContinue();
                }else{
                    System.out.println("Ingrese 's' o 'n' por favor");
                    enterContinue();
                }
            }while(!yesNo.equals("s") && !yesNo.equals("n"));
        }
    }

    public static void mostrarPagos(TriPoker juego, int asientos){
        double bonus, bet;
        if(asientos >=1) {
            System.out.println("\nMano 1:");
            if (juego.pagarBonus(juego.getMano1()) > 0) {
                bonus = juego.pagarBonus(juego.getMano1());
                System.out.println("GANA BONUS: $" + bonus);
            } else if(juego.getMano1().getBonus() == 0){
                System.out.println("BONUS NO JUGADO");
            }else{
                System.out.println("PIERDE BONUS");
            }

            if (juego.jugadorGana(juego.getMano1()) > 0) {
                bet = juego.pagarBet(juego.getMano1());
                System.out.println("GANA ANTE: $" + bet);
                System.out.println("GANA BET: $" + bet);
            } else if(juego.jugadorGana(juego.getMano1()) < 0 || juego.getMano1().getAnte() == 0){
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

            } else if(juego.getMano2().getBonus() == 0){
                System.out.println("BONUS NO JUGADO");
            }else{
                System.out.println("PIERDE BONUS");
            }
            if (juego.jugadorGana(juego.getMano2()) > 0) {
                bet = juego.pagarBet(juego.getMano2());
                System.out.println("GANA ANTE: $" + bet);
                System.out.println("GANA BET: $" + bet);

            } else if(juego.jugadorGana(juego.getMano2()) < 0 || juego.getMano2().getAnte() == 0){
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
            } else if(juego.getMano3().getBonus() == 0){
                System.out.println("BONUS NO JUGADO");
            }else{
                System.out.println("PIERDE BONUS");
            }
            if (juego.jugadorGana(juego.getMano3()) > 0) {
                bet = juego.pagarBet(juego.getMano3());
                System.out.println("GANA ANTE: $" + bet);
                System.out.println("GANA BET: $" + bet);

            } else if(juego.jugadorGana(juego.getMano3()) < 0 || juego.getMano3().getAnte() == 0){
                System.out.println("PIERDE ANTE");
                System.out.println("PIERDE BET");
            }else{
                System.out.println("ANTE Y BET: HAY EMPATE, RECUPERA SU APUESTA");
            }
        }
    }

    public static double sumaApostado(TriPoker juego){
        return juego.getMano1().getAnte() + juego.getMano1().getBonus() + juego.getMano1().getBet() +
                juego.getMano2().getAnte() + juego.getMano2().getBonus() + juego.getMano2().getBet() +
                juego.getMano3().getAnte() + juego.getMano3().getBonus() + juego.getMano3().getBet();
    }

    public static void comoJugar(){
        System.out.println("Como Jugar:\n\n" +
                "El juego de TriPoker es una variante del Poker pero se juega con 3 cartas\n" +
                "contra la banca. Se puede jugar a Bonus(apuesto a que en mis cartas voy a tener un par\n" +
                "o un juego mayor) y/o puedo jugar al Ante y competir contra las cartas de la banca.\n\n" +

                "Para poder jugar a TriPoker debe conocer los juegos de poker:\n" +
                "Carta alta: Corresponde a no tener otro juego y vale su carta mas alta,\n" +
                "Par: Corresponde a tener dos cartas del mismo valor,\n" +
                "Color: Corresponde a tener las 3 cartas del mismo palo,\n" +
                "Escalera: Corresponde a tener las 3 cartas seguidas ('As' cuenta como 1 y como 'As' despues de 'K',\n" +
                "TriPoker: Corresponde a tener las 3 cartas del mismo valor,\n" +
                "Escalera Color: Corresponde a tener escalera y color a la vez.\n\n" +

                "Pagos Bonus:\n" +
                "Par: 1x\n" +
                "Color: 4x\n" +
                "Escalera: 6x\n" +
                "TriPoker: 30x\n" +
                "Escalera Color: 40x\n\n" +

                "Pagos Ante y Bet:\n" +
                "Siempre x2 de lo apostado\n\n" +

                "Para jugar:\n" +
                "Primero vamos a ingresar en cuantas manos vamos a realizar apuestas (puedo tener de 1 a 3 manos diferentes)\n" +
                "luego vamos a ingresar la apuesta en el Bonus y Ante por mano, puedo jugar en uno, otro o ambos,\n" +
                "si no quiere jugar en alguno ingrese 0, si ingresa 0 en ambos va a poder jugar pero no recibira pago\n" +
                "el juego nos va a mostrar nuestras cartas y vamos a decidir si seguir jugando o no. Si anulamos la apuesta\n" +
                "perderemos lo ya apostado, si continuamos vamos a asignar el 'Bet' el mismo monto apostado ya en el Ante.\n" +
                "Con todo esto realizado el juego procede a mostrar la mano de la banca y decirnos si ganamos o perdimos\n\n" +
                "Gracias por jugar cualquier consulta puede dirigirse a algun administrador");
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

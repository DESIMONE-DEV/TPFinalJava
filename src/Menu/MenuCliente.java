package Menu;
import Modelo.Utiles.CodPassword;


import Modelo.Usuarios.Cliente;

import java.util.Scanner;

public class MenuCliente {
    public static Scanner sc = new Scanner(System.in);

    public static void menuInicialCliente(Cliente cliente) {
        System.out.println("Bienvenido a nuestro casino, " + cliente.getNombre());

        int opcion = -1;

        do{
            System.out.println("\n Menu Principal | Saldo actual: $: " + cliente.getSaldo());

            opcion = menuInicial();

            switch (opcion) {
                case 1:
                    menuJugar(cliente);
                    break;
                case 2:
                    cargarSaldo(cliente);
                    break;
                case 3:
                    retiroSaldo(cliente);
                    break;
                case 4:
                    cambiarCuentaBancaria(cliente);
                    break;
                case 5:
                    cambiarContrasenia(cliente);
                    break;
                case 6:
                    System.out.println(GestionMenu.stats.listarStats(cliente.getNombre(), 25));
                    System.out.println("Presione enter para continuar...");
                    sc.nextLine();
                    break;
                case 7:
                    System.out.println(GestionMenu.stats.listarStats(cliente.getNombre()));
                    System.out.println("Presione enter para continuar...");
                    sc.nextLine();
                case 0:
                    System.out.println("Cerrando sesion, hasta la proxima " + cliente.getNombre());
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente");
                    break;
            }



        }while(opcion!=0);
    }

    public static int menuInicial(){
        System.out.println("Elija una opcion");
        System.out.println("1. Lista de juegos");
        System.out.println("2. Cargar saldo");
        System.out.println("3. Retirar saldo");
        System.out.println("4. Cambiar cuenta bancaria");
        System.out.println("5. Cambiar contraseña");
        System.out.println("6. Ver Ultimos 25 movimientos");
        System.out.println("7. Ver todos los movimientos");
        System.out.println("0. Salir");

        try {
            String opcionLista = sc.nextLine();
            int opcion = Integer.parseInt(opcionLista);

            return opcion;
        }catch (NumberFormatException e){
            System.out.println("Error: debe ingresar un numero");
            return -1;
        }
    }

    ///---------- MENU DE JUEGOS ----------
    public static void menuJugar(Cliente cliente) {
        int opcionJuego = -1;

        do{
            System.out.println("\n Menu de juegos | Saldo actual: $: " + cliente.getSaldo());
            System.out.println("1. Jugar a la Ruleta");
            System.out.println("2. Jugar al TriPoker");
            System.out.println("3. Jugar al Blackjack");
            System.out.println("0. Salir al menu principal");

            try {
                System.out.println("Seleccione un juego:");
                String juego = sc.nextLine();
                opcionJuego = Integer.parseInt(juego);

                switch (opcionJuego) {
                    case 1:
                        System.out.println("Entrando a la ruleta...");

                        MenuRuleta.Start(cliente);

                        System.out.println("Saliendo de la ruleta...");
                        break;

                    case 2:
                        System.out.println("Entrando a TriPoker...");

                        MenuTriPoker.start(cliente);

                        System.out.println("Saliendo del TriPoker...");
                        break;

                    case 3:
                        System.out.println("Entrando a Blackjack...");

                        MenuBlackJack.start(cliente);

                        System.out.println("Saliendo del Blackjack...");
                        break;

                    case 0:
                        System.out.println("Saliendo al menu principal...");
                        break;
                    default:
                        System.out.println("Opcion invalida, intente nuevamente");
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("Error: debe ingresar un numero");
                opcionJuego = -1;
            }
        }while(opcionJuego!=0);
    }
    ///---------- MENU DE CARGO DE SALDO ----------
    ///
    public static void cargarSaldo(Cliente cliente) {
        System.out.println("Carga de saldo");
        System.out.println("Saldo actual: " + cliente.getSaldo());

        try{
            System.out.println("Ingrese el monto que quiera cargar");
            String monto = sc.nextLine();
            Double montoDouble = Double.parseDouble(monto);

            if(montoDouble <= 0){
                System.out.println("Error: el monto debe ser positivo");
                return;
            }
            cliente.cargarSaldo(montoDouble);
            GestionMenu.crearStats(cliente.getNombre(), "Carga de Saldo", montoDouble);



            System.out.println("Carga realizada exitosamente!");
            System.out.println("Saldo actual: " + cliente.getSaldo());
        } catch (NumberFormatException e){
            System.out.println("Error: debe ingresar un numero valido");
        }
    }
    ///---------- MENU DE RETIRO DE SALDO ----------
    public static void retiroSaldo(Cliente cliente) {
        System.out.println("Retiro de saldo");
        System.out.println("Saldo actual: " + cliente.getSaldo());

        try{
            System.out.println("Ingrese el monto que quiera retirar");
            String monto = sc.nextLine();
            Double montoDouble = Double.parseDouble(monto);

            if(montoDouble <= 0){
                System.out.println("Error: el monto debe ser positivo");
                return;
            }

            if(cliente.getSaldo() < montoDouble){
                System.out.println("Error: saldo insuficiente para retirar");
            }else {
                cliente.retirarSaldo(montoDouble);
                GestionMenu.crearStats(cliente.getNombre(), "Retiro de saldo", (-1)*montoDouble);
                System.out.println("Carga realiada exitosamente!");
                System.out.println("Saldo actual: " + cliente.getSaldo());
            }

        } catch (NumberFormatException e){
            System.out.println("Error: debe ingresar un numero valido");
        }
    }
    ///---------- MENU DE CAMBIO DE CUENTA BANCARIA ----------
    public static void cambiarCuentaBancaria(Cliente cliente) {
        System.out.println("Cambiar cuenta bancaria");
        System.out.println("Su cuenta actual es: " + cliente.getCuentaBancaria());
        System.out.println("Ingrese su nueva cuenta bancaria:");
        String nuevaCuentaBancaria = sc.nextLine();

        cliente.setCuentaBancaria(nuevaCuentaBancaria);

        System.out.println("Cuenta bancaria actualizada: " + cliente.getCuentaBancaria());
    }
    ///---------- MENU DE CAMBIO DE CONTRASEÑA ----------
    public static void cambiarContrasenia(Cliente cliente) {
        System.out.println("Cambiar Contrasenia");
        System.out.println("Ingrese su contrasenia actual:");
        String contrasenia = sc.nextLine();

        if(cliente.getPassword().equals(contrasenia)){
            System.out.println("Ingrese una nueva contrasenia");
            String nuevaContrasenia = sc.nextLine();
            System.out.println("Confirme su nueva contrasenia");
            String nuevaContrasenia2 = sc.nextLine();

            if (nuevaContrasenia.equals(nuevaContrasenia2)) {
                if(nuevaContrasenia.isEmpty()){
                    System.out.println("Error. La contrasenia no puede estar vacia");
                } else {
                    cliente.setPassword(CodPassword.codificarPassword(nuevaContrasenia));
                }
            } else {
                System.out.println("Contrasenias no coinciden");
            }
        }else {
            System.out.println("Contrasenia incorrecta");
        }
    }
}

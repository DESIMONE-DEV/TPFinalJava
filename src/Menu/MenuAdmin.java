package Menu;

import Exceptions.ColleccionVaciaException;
import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdmin {
    public static Scanner sc = new Scanner(System.in);

    public static void menuInicialAdmin(Admin admin) {
        System.out.println("Bienvenido a nuestro casino, " + admin.getNombre());

        int opcion = -1;

        do {
            System.out.println("\n Menu Principal ");

            opcion = menuInicial();

            switch (opcion) {
                case 1:
                    listarUsuarios();
                    break;
                case 2:
                    /// Ingresos
                    break;
                case 3:
                    /// Retiros
                    break;
                case 4:

                    break;
                case 5:
                    cargarSaldo(a);
                    break;
                case 6:
                    cambiarEstadoBaneo(a)
                    break;
                case 0:
                    System.out.println("Cerrando sesion, hasta la proxima " + admin.getNombre());
                    return;
                default:
                    System.out.println("Opcion invalida, intente nuevamente");
                    break;
            }


        } while (true);
    }
    public static int menuInicial(){
        System.out.println("Elija una opcion");
        System.out.println("1. Lista de usuarios");
        System.out.println("2. Ingresos");
        System.out.println("3. Retiros");
        System.out.println("4. Cambiar password de usuario");
        System.out.println("5. Cargar saldo a usuario");
        System.out.println("6. Bloquear/Desbloquear usuario");
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

    public static void listarUsuarios () {
        try {
            String listaUsuarios = GestionMenu.User.listar();
            System.out.println("Lista de usuarios: \n" + listaUsuarios);
        }catch (ColleccionVaciaException e){
            System.out.println("No hay usuarios en el sistema");
        }

    }

    public static void CargarSaldo(Admin a) {
        try{
            System.out.println("Ingrese el DNI del cliente a modificar el saldo: ");
            int dni = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingrese el monto a cargar: ");
            double monto = sc.nextDouble();
            sc.nextLine();

            Cliente buscado = new Cliente(dni);

            Usuario encontrado = GestionMenu.User.getDato(buscado);

            if(encontrado != null && encontrado instanceof Cliente){
                Cliente real = (Cliente) encontrado;

                a.agregarFichas(real, monto);

                System.out.println("Saldo cargado");
                System.out.println("Nuevo saldo de " + real.getNombre() + ":" + real.getSaldo());

            } else {
                System.out.println("Cliente no encontrado");
            }
        } catch (InputMismatchException e){
            System.out.println("Error: DNI y monto deben ser numericos");
        } catch (ColleccionVaciaException e){
            System.out.println("Error. No hay usuarios");
        }
        
        
    }

    public static void cambiarEstadoBaneo(Admin a) {
        try{
            System.out.println("Ingrese el DNI del cliente a banear / desbanear: ");
            int dni = sc.nextInt();
            sc.nextLine();

            Cliente buscado = new Cliente(dni);

            Usuario encontrado = GestionMenu.User.getDato(buscado);

            if(encontrado != null && encontrado instanceof Cliente){
                Cliente real = (Cliente) encontrado;

                System.out.println("Que desea realizar?");
                System.out.println("1. Bloquear usuario");
                System.out.println("2. Desbloquear usuario");
                int opc = sc.nextInt();
                sc.nextLine();

                boolean estado;
                if(opc == 1){
                    estado = false;
                    System.out.println("Cuenta bloqueada");
                } else {
                    estado = true;
                    System.out.println("Cuenta activada");
                }

                a.bloquear(real, estado);

            } else {
                System.out.println("Cliente no encontrado");
            }
        } catch (InputMismatchException e){
            System.out.println("Error: DNI debe ser numericos");
        } catch (ColleccionVaciaException e){
            System.out.println("Error. No hay usuarios");
        }
        
        
    }



}
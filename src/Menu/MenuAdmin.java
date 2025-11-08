package Menu;

import Exceptions.ColleccionVaciaException;
import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;

import java.util.Scanner;

public class MenuAdmin {
    public static Scanner sc = new Scanner(System.in);

    public static void menuInicialAdmin(Admin admin) {
        System.out.println("Bienvenido a nuestro casino, " + admin.getNombre());

        int opcion = -1;

        do {
            System.out.println("\n Menu Principal ");

            switch (opcion) {
                case 1:
                    /// Listar Usuarios
                    break;
                case 2:
                    /// Ingresos
                    break;
                case 3:
                    /// Retiros
                    break;
                case 4:
                    /// Cambiar password de Usuario que se aolvido
                    break;
                case 5:
                    /// cargar saldo
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

    public static void listarUsuarios () throws ColleccionVaciaException {
        try {
            String listaUsuarios = GestionMenu.User.listar();
            System.out.println("Lista de usuarios: \n" + listaUsuarios);
        }catch (ColleccionVaciaException e){
            System.out.println("No hay usuarios en el sistema");
        }

    }


}
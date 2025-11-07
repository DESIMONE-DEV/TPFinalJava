package Menu;

import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;

public class MenuAdmin {

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
}
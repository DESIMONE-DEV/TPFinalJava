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

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 0:
                    System.out.println("Cerrando sesion, hasta la proxima " + admin.getNombre());
                    break;
                default:
                    System.out.println("Opcion invalida, intente nuevamente");
                    break;
            }


        } while (opcion != 0);
    }
}
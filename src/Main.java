import Menu.MenuPrincipal;
import Modelo.Gestores.GestorJson;
import Modelo.Usuarios.Cliente;

import static Menu.GestionMenu.*;
import static Menu.MenuPrincipal.creditos;

public class Main {
    public static void main(String[] args) {

        GestorJson.cargajson();
        GestorJson.cargarEstadisticas();

        do {

            switch (opcionesMenu()) {
                case 1:
                    loguearCuenta();
                    break;
                case 2:
                    int opcion;
                    do {
                        opcion = opcionCuentaClientAdmin();

                        switch (opcion) {
                            case 1:
                                crearCuentaCliente();
                                break;
                            case 2:
                                crearCuentaAdmin();
                                break;
                            case 0:
                                System.out.println("Volviendo al menú principal...");
                                break;
                            default:
                                System.out.println("Opción inválida");
                                break;
                        }

                    }while(opcion != 0);
                    break;
                case 3:
                       creditos();
                    break;
                case 4:
                    System.out.println("No se vaya, la suerte esta de su lado");
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }

        }while(true);

    }
}
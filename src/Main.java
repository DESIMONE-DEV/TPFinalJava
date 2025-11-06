import Menu.MenuPrincipal;

import static Menu.GestionMenu.*;

public class Main {
    public static void main(String[] args) {


        do {

            switch (opcionesMenu()) {
                case 1:
                    loguearCuenta();
                    break;
                case 2:
                            switch (opcionCuentaClientAdmin()) {
                                case 1:
                                    crearCuentaCliente();
                                    break;
                                    case 2:
                                        crearCuentaAdmin();
                                        break;

                            }
                    break;
                case 3:
                       try{
                           System.out.println(User.listar());
                       }catch(Exception e){
                           System.out.println(e.getMessage());
                       }
                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;

            }

        }while(true);


    }
}
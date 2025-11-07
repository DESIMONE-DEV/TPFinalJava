import Menu.MenuPrincipal;
import Modelo.Usuarios.Cliente;

//import static Menu.GestionMenu.*;

public class Main {
    public static void main(String[] args) {
/*

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

*/
        Cliente ferchoElVicioso = new Cliente("Fercho", 42526262, "f123", "BritoCoin");

        ferchoElVicioso.setSaldo(1000.00);

        System.out.println("Prueba con: " + ferchoElVicioso.getNombre());
        System.out.println("Saldo: " + ferchoElVicioso.getSaldo());




    }
}
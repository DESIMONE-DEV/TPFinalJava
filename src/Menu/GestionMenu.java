package Menu;

import java.io.IO;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionMenu {

    static Scanner sc = new Scanner(System.in);

    /// ------------------METODO OPCIONES MENU CON EXCEPCION INPUT------////
    public static int opcionesMenu(){

        IO.println("1: Login");
        IO.println("2: Create Account");
        IO.println("3: Creditos");
        IO.println("4: Salir");

        try {
            return sc.nextInt();
        }catch(InputMismatchException e){
            System.out.println("No sea  malo ingrese un numero");
        }finally {
            sc.nextLine();
        }
       return 0;

    }
    /// ----------------FIN METODO OPCIONES MENU CON EXCEPCION INPUT------////
}


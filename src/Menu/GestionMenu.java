package Menu;

import Exceptions.CampoVacioException;
import Exceptions.CaracteresMaximoException;
import Exceptions.CaracteresMinimoException;
import Modelo.Gestores.GestorGenerico;
import java.io.IO;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static Menu.MenuPrincipal.createAccountCLient;


public class GestionMenu {

    static Scanner sc = new Scanner(System.in);
   public static GestorGenerico User = new GestorGenerico();

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

    /// ------------------METODO CREACION/SELECION TIPO DE CUENTA CON EXCEPCION INPUT------////
    public static int opcionCuentaClientAdmin(){

        IO.println("1: Create Client Account");
        IO.println("2: Create Admin Account");

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
    ///
    /// --------------INGRESO DATOS CREATE ACCOUNT -------------///
    ///
    /// /// --------------ACCOUNT CLIENTE -------------///
    public static void crearCuentaCliente(){

        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Ingrese DNI: ");
        int dni=0;
        try{
            dni = sc.nextInt();
            sc.nextLine();
        }catch(InputMismatchException e){
            System.out.println("No sea  malo ingrese un numero");
        }

        System.out.print("Ingrese password: ");
        String password = sc.nextLine();


        System.out.print("Ingrese CBU: ");
        String cta = sc.nextLine();

        try {

            createAccountCLient(nombre, dni, password, cta);

        }catch (CaracteresMinimoException e){
            System.out.println(e.getMessage());
        }catch (CaracteresMaximoException e){
            System.out.println(e.getMessage());
        }catch (CampoVacioException e){
            System.out.println(e.getMessage());
        }

    }
    ///
    /// /// --------------ACCOUNT ADMIN -------------///

    /// -----------------FIN INGRESO DATOS CREATE ACCOUNT------------------///

 }


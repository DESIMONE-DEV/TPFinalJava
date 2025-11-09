package Menu;

import Exceptions.*;
import Modelo.Gestores.GestorGenerico;
import Modelo.Gestores.GestorStats;
import Modelo.Stats.Estadistica;
import Modelo.Usuarios.Cliente;

import java.util.InputMismatchException;
import java.util.Scanner;

import static Menu.MenuPrincipal.*;


public class GestionMenu {

    public static Scanner sc = new Scanner(System.in);
    public static GestorGenerico User = new GestorGenerico();
    public static GestorStats stats = new GestorStats();

    /// ------------------METODO OPCIONES MENU CON EXCEPCION INPUT------////
    public static int opcionesMenu(){

        System.out.println("1: Login");
        System.out.println("2: Create Account");
        System.out.println("3: Creditos");
        System.out.println("4: Salir");

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

        System.out.println("1: Create Client Account");
        System.out.println("2: Create Admin Account");

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
    ///
    public static void crearCuentaAdmin(){

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
        System.out.print("Ingrese CODIGO MEGASECRETO: ");
        String cod = sc.nextLine();

        try {
            createAccountAdmin(nombre, dni, password,cod);
        }catch (CaracteresMinimoException e){
            System.out.println(e.getMessage());
        }catch (CaracteresMaximoException e){
            System.out.println(e.getMessage());
        }catch (CampoVacioException e){
            System.out.println(e.getMessage());
        }catch (CodigoIncorrectoException e){
            System.out.println(e.getMessage());
        }

    }
    /// -----------------FIN INGRESO DATOS CREATE ACCOUNT------------------///
    ///
    /// -----------------METODO INGRESO DE DATOS lOGUEO ------------------///
    ///
    public static void loguearCuenta(){

        int dni=0;
        String password="a";

        try{
            System.out.print("Ingrese DNI: ");
            dni = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese password: ");
            password= sc.nextLine();

        }catch(InputMismatchException e) {
            System.out.println("No sea  malo ingrese un numero correcto");
        }

        try {
           mLoginUsuario(dni, password,User);
        } catch (UsuarioContraseñaIncorrectaException e) {
            System.out.println(e.getMessage());
        }

    }

    ///
    /// -----------------FIN METODO INGRESO DE DATOS LOGUEO-----------------///

    /// ---------------- CREACION ESTADISTICA NUEVA ---------------------- ////

    public static void crearStats(int jugador, String tipoMovimiento, double monto){
        stats.agregarStats(new Estadistica(jugador, tipoMovimiento, monto));
    }
 }


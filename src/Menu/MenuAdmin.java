package Menu;

import Exceptions.ColleccionVaciaException;
import Modelo.Usuarios.Admin;
import Modelo.Usuarios.Cliente;
import Modelo.Usuarios.Usuario;
import Modelo.Utiles.CodPassword;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuAdmin {
    public static Scanner sc = new Scanner(System.in);

    public static void menuInicialAdmin(Admin admin) {

        int opcion = -1;

        do {
            System.out.println("Bienvenido, " + admin.getNombre());
            System.out.println("Menu Principal \n");

            opcion = menuInicial();

            switch (opcion) {
                case 1:
                    listarUsuarios();
                    break;
                case 2:
                    mostrarStats();
                    break;
                case 3:
                    mostrarStatsTotales();
                    break;
                case 4:
                    cambiarContraseniaUser();
                    break;
                case 5:
                    CargarSaldo(admin);
                    break;
                case 6:
                    cambiarEstadoBaneo(admin);
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
    ///---------- MENU BASICO DE ADMIN ----------
    public static int menuInicial(){
        System.out.println("1. Lista de usuarios");
        System.out.println("2. Ultimos 10 movimientos de un usuario");
        System.out.println("3. Todos los movimientos de un usuario");
        System.out.println("4. Cambiar password de usuario");
        System.out.println("5. Cargar saldo a usuario");
        System.out.println("6. Bloquear/Desbloquear usuario");
        System.out.println("0. Salir");

        System.out.println("Ingrese una opcion:");

        try {
            int opcion = sc.nextInt();
            sc.nextLine();

            return opcion;
        }catch (InputMismatchException e){
            System.out.println("Error: debe ingresar un numero");
            sc.nextLine();
            return -1;
        }
    }
    ///---------- LISTADO DE USUARIOS -----------
    public static void listarUsuarios () {
        try {
            String listaUsuarios = GestionMenu.User.listar();
            System.out.println("Lista de usuarios: \n" + listaUsuarios);
        }catch (ColleccionVaciaException e){
            System.out.println("No hay usuarios en el sistema");
        }

    }
    ///---------- MOSTRAR ULTIMOS 10 MOVIMIENTOS DE UN SOLO USUARIO -----------
    public static void mostrarStats (){
        try{
            System.out.println("Ingrese el DNI del usuario para ver sus movimientos");
            int dni = sc.nextInt();
            sc.nextLine();

            Cliente buscado = new Cliente(dni);

            Usuario encontrado = (Usuario) GestionMenu.User.getDato(buscado);

            if(encontrado != null && encontrado instanceof Cliente) {
                String stats = GestionMenu.stats.listarStats(dni, 10);
                System.out.println(stats);
            }else if(encontrado != null && encontrado instanceof Admin){
                System.out.println("Usuario Admin, no tiene movimientos!");
            }else{
                System.out.println("Cliente no encontrado");
            }
        } catch (InputMismatchException e){
            System.out.println("Error: el DNI debe ser numerico");
            sc.nextLine();
        }catch (ColleccionVaciaException e){
                System.out.println("Error. No hay usuarios");
            }
    }
    ///---------- MOSTRAR TODOS LOS MOVIMIENTOS DE UN USUARIO -----------
    public static void mostrarStatsTotales (){
        try{
            System.out.println("Ingrese el DNI del usuario para ver TODOS sus movimientos");
            int dni = sc.nextInt();
            sc.nextLine();

            Cliente buscado = new Cliente(dni);

            Usuario encontrado = (Usuario) GestionMenu.User.getDato(buscado);

            if(encontrado != null && encontrado instanceof Cliente) {
                String stats = GestionMenu.stats.listarStats(dni);
                System.out.println(stats);
            }else if(encontrado != null && encontrado instanceof Admin){
                System.out.println("Usuario Admin, no tiene movimientos!");
            }else{
                System.out.println("Cliente no encontrado");
            }
        } catch (InputMismatchException e){
            System.out.println("Error: el DNI debe ser numerico");
            sc.nextLine();
        }catch(ColleccionVaciaException e){
            System.out.println("Error. No hay usuarios");
        }
    }
    ///---------- RESTAURAR CONTRASEÑA DE USUARIO -----------
    public static void cambiarContraseniaUser(){
        try{
            System.out.println("Ingrese el DNI del usuario a modificar");
            int dni = sc.nextInt();
            sc.nextLine();

            Cliente buscado = new Cliente(dni);

            Usuario encontrado = (Usuario) GestionMenu.User.getDato(buscado);

            if(encontrado != null){
                System.out.println("Ingrese nueva contraseña para " + encontrado.getNombre());
                String nuevaContrasenia = sc.nextLine();

                if (nuevaContrasenia.isEmpty()){
                    System.out.println("Error. La contrasenia no puede estar vacia");
                } else {
                    encontrado.setPassword(CodPassword.codificarPassword(nuevaContrasenia));
                    GestionMenu.guardadoAutomatico();
                    System.out.println("Contraseña reestablecida correctamente");
                }
            } else {
                System.out.println("Usuario no encontrado");
            }
        } catch (InputMismatchException e){
            System.out.println("Error: el DNI debe ser numerico");
        } catch (ColleccionVaciaException e){
            System.out.println("No hay usuarios en el sistema");
        }
    }
    ///---------- CARGAR SALDO A UN USUARIO -----------
    public static void CargarSaldo(Admin a) {
        try{
            System.out.println("Ingrese el DNI del cliente a modificar el saldo: ");
            int dni = sc.nextInt();
            sc.nextLine();

            System.out.println("Ingrese el monto a cargar: ");
            double monto = sc.nextDouble();
            sc.nextLine();

            Cliente buscado = new Cliente(dni);

            Usuario encontrado = (Usuario) GestionMenu.User.getDato(buscado);

            if(encontrado != null && encontrado instanceof Cliente){
                Cliente real = (Cliente) encontrado;

                a.agregarFichas(real, monto);

                System.out.println("Saldo cargado");
                System.out.println("Nuevo saldo de " + real.getNombre() + ":" + real.getSaldo());

                GestionMenu.crearStats(real.getDni(),"Carga Admin "+ a.getNombre(),monto);
                GestionMenu.guardadoAutomatico();
            } else {
                System.out.println("Cliente no encontrado");
            }

        } catch (InputMismatchException e){
            System.out.println("Error: DNI y monto deben ser numericos");
        } catch (ColleccionVaciaException e){
            System.out.println("Error. No hay usuarios");
        }
        
        
    }
    ///---------- BOQUEAR / DESBLOQUEAR CLIENTE -----------
    public static void cambiarEstadoBaneo(Admin a) {
        try{
            System.out.println("Ingrese el DNI del cliente a banear / desbanear: ");
            int dni = sc.nextInt();
            sc.nextLine();

            Cliente buscado = new Cliente(dni);

            Usuario encontrado = (Usuario) GestionMenu.User.getDato(buscado);

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
                GestionMenu.guardadoAutomatico();

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
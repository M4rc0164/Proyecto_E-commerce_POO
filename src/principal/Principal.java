package principal;
import Pagos.*;
import datos.*;
import java.util.Scanner;
import static principal.ProductosInciales.agregarProductos;
import usuarios.*;


/**
 * @author marco
 */
public class Principal {
    public static void main(String[] args) {
        agregarProductos();
                
                
        Scanner scan = new Scanner(System.in);
        int opt1 = 0;
        char confirmacion = 'n';
        do {
            System.out.println("\t=========== Plataforma de ecommerce ===========");
            System.out.println("\t------ Menú principal ------");
            System.out.println("1. Ingresar al sistema");
            System.out.println("2. Registrarse como cliente");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción deseada: ");
            opt1 = scan.nextInt();
            scan.nextLine();
            switch(opt1){
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarCliente();
                    break;
                case 3:
                    System.out.print("Confirmar salida? S/N: ");
                    confirmacion = scan.next().charAt(0);
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (confirmacion != 's' && confirmacion != 'S');

    }
    
    public static void registrarCliente(){
        BaseDatos baseDatos = BaseDatos.getBaseDatos();
        String user, contrasena, nombre;
        Usuario usuario;
        
        Scanner lector = new Scanner(System.in);
        
        System.out.println("Ingresa el usuario con el que te vas a registrar:");
        user = lector.nextLine().trim();
        
        usuario = baseDatos.buscarUsuario(user);
        
        if(usuario != null){
            System.out.println("Ya existe el usuario, intentalo de nuevo");
            return;
        }
        
        System.out.println("Ingresa tu nombre: ");
        nombre= lector.nextLine().trim();
        
        System.out.print("Ingresa tu contraseña: ");
        contrasena = lector.nextLine().trim();
        baseDatos.agregarUsuario(new Cliente(user, nombre, contrasena));
        System.out.println("Usuario registrado correctamente");    
    }
    
    public static Usuario iniciarSesion(){    
        BaseDatos baseDatos = BaseDatos.getBaseDatos();
        String user, contrasena;
        Usuario usuario;
        
        Scanner lector = new Scanner(System.in);
        
        System.out.print("Ingresa tu usuario: ");
        user = lector.nextLine().trim();
        usuario = baseDatos.buscarUsuario(user);
        
        if(usuario == null){
            System.out.println("No existe el usuario");
            return null;
        }
        
        System.out.print("Ingresa tu contraseña: ");
        contrasena = lector.nextLine().trim();
        if(usuario.validarPassword(contrasena)){
            System.out.println("Contraseña correcta");
        }else{
            System.out.println("Contraseña incorrecta, intentalo de nuevo");
            return null;
        }
        
        System.out.println("Sesion iniciada correctamente");
        MenuUsuario menuUsuario=new MenuUsuario(usuario);
        menuUsuario.menuCliente();
        return usuario;
    }
    
}

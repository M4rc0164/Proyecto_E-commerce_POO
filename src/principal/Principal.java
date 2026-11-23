package principal;
import Pagos.*;
import datos.*;
import usuarios.*;
import utilidades.LeerDatos;


/**
 * @author marco
 */
public class Principal {
    public static void main(String[] args) {
        int opt1 = 0;
        char confirmacion = 'n';
        do {
            System.out.println("\t=========== Plataforma de ecommerce ===========");
            System.out.println("\t------ Menú principal ------");
            System.out.println("1. Ingresar al sistema");
            System.out.println("2. Registrarse como cliente");
            System.out.println("3. Salir");
            // Se reemplazó scan.nextInt() por LeerDatos.pedirEntero() 
            opt1 = LeerDatos.pedirEntero("Ingrese la opción deseada: ");
            switch(opt1){
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarCliente();
                    break;
                case 3:
                    // Se usa LeerDatos.pedirTexto() para leer la confirmación de forma segura y evitar múltiples Scanner
                    String resp = LeerDatos.pedirTexto("Confirmar salida? S/N: ");
                    if (!resp.isEmpty()) {
                        confirmacion = resp.charAt(0);
                    }
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
        
        // Se usa LeerDatos.pedirTexto() y se agregaron validaciones para impedir usuarios o nombres vacíos
        user = LeerDatos.pedirTexto("Ingresa el usuario con el que te vas a registrar: ");
        if (user.isEmpty()) {
            System.out.println("El nombre de usuario no puede estar vacío");
            return;
        }
        
        usuario = baseDatos.buscarUsuario(user);
        
        if(usuario != null){
            System.out.println("Ya existe el usuario, intentalo de nuevo");
            return;
        }
        
        nombre = LeerDatos.pedirTexto("Ingresa tu nombre: ");
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío");
            return;
        }
        
        contrasena = LeerDatos.pedirTexto("Ingresa tu contraseña: ");
        baseDatos.agregarUsuario(new Cliente(user, nombre, contrasena));
        System.out.println("Usuario registrado correctamente");    
    }
    
    public static void iniciarSesion(){    
        BaseDatos baseDatos = BaseDatos.getBaseDatos();
        String user, contrasena;
        Usuario usuario;
        
        user = LeerDatos.pedirTexto("Ingresa tu usuario: ");
        usuario = baseDatos.buscarUsuario(user);
        
        if(usuario == null){
            System.out.println("No existe el usuario");
            return;
        }
        
        contrasena = LeerDatos.pedirTexto("Ingresa tu contraseña: ");
        if(usuario.validarPassword(contrasena)){
            System.out.println("Contraseña correcta");
        }else{
            System.out.println("Contraseña incorrecta, intentalo de nuevo");
            return;
        }
        
        MenuUsuario menuUsuario = new MenuUsuario(usuario);
        if (usuario instanceof Admin) {
            menuUsuario.menuAdmin();
        } else {
            menuUsuario.menuCliente();
        }
    }
    
}

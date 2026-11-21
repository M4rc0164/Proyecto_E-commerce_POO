package principal;
import datos.BaseDatos;
import java.util.Scanner;
import usuarios.Usuario;
/**
 *
 * @author dell2
 */
public class MenuSistema {
    Scanner lector=new Scanner(System.in);
    public void pedirFormaDePago(){
        int opcion=0;
        System.out.println("Favor de ingresar la forma de pago para sus productos");
        System.out.println("Selecciona alguna de las 2 opciones");
        System.out.println("1.Paypal\n2.TarjetaDeCredito");
        opcion=lector.nextInt();
        switch(opcion){
            case 1:
                System.out.println("Favor de ingresar el usuario");
        }
    }
    
    public void funcionalidadesDelCliente(){
        int opcion=0;
        System.out.println("Favor de ingresar alguna de las siguientes opciones disponibles");
        System.out.println("1. Agregar producto");
        System.out.println("2. Registrar domicilio");
        System.out.println("3. Registrar medio de pago");
        System.out.println("4. Confirmar pedido");
        System.out.println("5. Vaciar carrito");
        System.out.println("6. Consultar carrito");
        System.out.println("7. Imprimir ticket de un pedido");
        System.out.println("8. Salir");
        opcion=lector.nextInt();
        switch(opcion){
            case 1:
                break;
            case 2:
                break;
            case 3:
                pedirFormaDePago();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            default:
                System.out.println("Error, favor de ingresar un numero entero");
                break;
        }
    }
    
}

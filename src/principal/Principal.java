package principal;

import java.util.Scanner;

/**
 * @author marco
 */
public class Principal {

    public static void main(String[] args) {
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
                    break;
                case 2:
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

}

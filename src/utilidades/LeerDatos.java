/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import java.util.Scanner;

/**
 *
 * @author EdelM
 */
public class LeerDatos {
    static Scanner lector = new Scanner(System.in);
    
    public static int pedirEntero(String mensaje) {
        int x = 0;
        boolean validador;

        do {
            System.out.print(mensaje);
            validador = lector.hasNextInt();

            if (validador) {
                x = lector.nextInt();
            } 
            lector.nextLine(); 
            
        } while (!validador);

        return x; 
    }
}

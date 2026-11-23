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
    // Se encapsuló como private static final para centralizar la lectura de System.in
    private static final Scanner lector = new Scanner(System.in);
    
    public static int pedirEntero(String mensaje) {
        int x = 0;
        boolean validador;

        do {
            System.out.print(mensaje);
            validador = lector.hasNextInt();

            if (validador) {
                x = lector.nextInt();
            } else {
                // Se agregó aviso al usuario cuando ingresa un valor no numérico
                System.out.println("Entrada inválida, ingresa un número entero.");
            }
            lector.nextLine(); 
            
        } while (!validador);

        return x; 
    }
    
    public static float pedirFlotante(String mensaje) {
        float x = 0;
        boolean validador;

        do {
            System.out.print(mensaje);
            validador = lector.hasNextFloat();

            if (validador) {
                x = lector.nextFloat();
            } else {
                // Se agregó aviso al usuario cuando ingresa un valor no numérico
                System.out.println("Entrada inválida, ingresa un número válido.");
            }
            lector.nextLine(); 
            
        } while (!validador);

        return x; 
    }

    // Método agregado para leer cadenas con el mismo Scanner 
    public static String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return lector.nextLine().trim();
    }
}

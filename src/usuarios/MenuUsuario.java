/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

import utilidades.LeerDatos;

/**
 *
 * @author EdelM
 */
public class MenuUsuario {
    
    public static void menuCliente(){
        int op;
        
        while(true){
            op = LeerDatos.pedirEntero("==Cliente==\n1)Agregar producto al carrito\n2)Registrar domicilo\n3)Registrar medio de Pago\n4)Confirmar pedido\n5)Vaciar carrito\n6)Consultar carrito\n7) Imprimir ticket de un pedido\n8)Salir");
            switch (op) {
                case 1: 
                    break;
                case 2: 
                    break;
                case 3: 
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
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        
        }
        
    }
    
    public static void menuAdmin(){
        int op;
        while(true){
            op = LeerDatos.pedirEntero("==Administrador==\n1)Alta de productos\nModificación de precios\n3)Salir");
            switch (op) {
                case 1: 
                    break;
                case 2: 
                    break;
                case 3: 
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        
        }
    }
}

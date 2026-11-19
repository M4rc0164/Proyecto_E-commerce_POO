/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

import datos.*;
import java.util.Scanner;
import utilidades.LeerDatos;

/**
 *
 * @author EdelM
 */
public class MenuUsuario {
    private Scanner lector;
    private Usuario usuario;
    private BaseDatos baseDatos;

    public MenuUsuario(Usuario usuario) {
        this.lector = new Scanner(System.in);
        this.usuario = usuario;
        this.baseDatos = BaseDatos.getBaseDatos();
    }

    
    
    
    public void menuCliente(){
        Cliente cliente = (Cliente) this.usuario;
        int op;
        
        while(true){
            op = LeerDatos.pedirEntero("==Cliente==\n1)Agregar producto al carrito\n2)Registrar domicilo\n3)Registrar medio de Pago\n4)Confirmar pedido\n5)Vaciar carrito\n6)Consultar carrito\n7) Imprimir ticket de un pedido\n8)Salir");
            switch (op) {
                case 1: {
                    this.baseDatos.imprimirProductos();
                    
                    System.out.print("Ingresa el id del producto que se añadirá al carrito: ");
                    Producto producto = this.baseDatos.buscarProducto(lector.nextLine());
                    if(producto == null){
                        System.out.println("No existe un producto con ese id");
                        break;
                    }
                    
                    cliente.getCarrito().agregarProducto(producto);
                    
                    break;
                    }
                case 2: 
                    break;
                case 3: 
                    break;
                case 4: 
                    break;
                case 5: 
                    break;
                case 6: 
                    cliente.getCarrito().mostrarCarrito();
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
    
    public void menuAdmin(){
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

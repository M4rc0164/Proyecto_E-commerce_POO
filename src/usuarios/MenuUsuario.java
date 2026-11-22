/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

import Pagos.Paypal;
import java.util.ArrayList;
import Pagos.TarjetaDeCredito;
import usuarios.*;
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
            op = LeerDatos.pedirEntero("==Cliente==\n1)Agregar producto al carrito\n2)Registrar domicilo\n3)Registrar medio de Pago\n4)Confirmar pedido\n5)Vaciar carrito\n6)Consultar carrito\n7)Imprimir ticket de un pedido\n8)Salir\n");
            switch (op) {
                case 1: {
                    this.baseDatos.imprimirProductos();
                    
                    System.out.print("Ingresa el id del producto que se añadirá al carrito: ");
                    String l = lector.nextLine();
                    Producto producto = this.baseDatos.buscarProducto(l);
                    if(producto == null){
                        System.out.println("No existe un producto con ese id");
                        break;
                    }
                    cliente.getCarrito().agregarProducto(producto);
                    
                    break;
                    }
                case 2: {
                    System.out.print("Ingresa la calle: ");
                    String calle = lector.nextLine().trim();
                    System.out.print("Ingresa la colonia: ");
                    String colonia = lector.nextLine().trim();
                    System.out.print("Ingresa el teléfono: ");
                    String telefono = lector.nextLine().trim();
                            
                    Domicilio domicilio = new Domicilio(calle, colonia, telefono);

                    if (cliente.agregarDomicilio(domicilio)) {
                        System.out.println("Domicilio registrado correctamente.");
                    }
                    
                    break;
                }
                case 3: 
                    int opcion=0;
                    System.out.println("Registrar medio de pago");
                    while(opcion!=1&&opcion!=2){
                        System.out.println("1.Paypal\n2.Tarjeta de credito");
                        opcion=lector.nextInt();
                    }
                    switch(opcion){
                    case 1:
                        System.out.println("Favor de ingresar un correo electronico");
                        String correoElectronico=lector.next();
                        System.out.println("Favor de ingresar su contrasena del correo electronico");
                        String contrasenaCorreoElectronico=lector.next();
                        Paypal paypal=new Paypal(correoElectronico, contrasenaCorreoElectronico, cliente.getUser());
                        cliente.getListaMediosPago().add(paypal);
                        break;
                    case 2:
                        System.out.println("Favor de ingresar numero de tarjeta de credito");
                        String numeroDeTarjetaDeCredito=lector.next();
                        System.out.println("Favor de ingresar el NIP de su tarjeta de credito");
                        String NIP=lector.next();
                        TarjetaDeCredito tarjetaDeCredito=new TarjetaDeCredito(cliente.getUser(), numeroDeTarjetaDeCredito, NIP);
                        cliente.getListaMediosPago().add(tarjetaDeCredito);
                        break;
                    default:
                        System.out.println("Error, favor de ingresar alguna de las opciones");
                    break;
                    }
                    break;
                case 4:                     
                    Pedido pedido = cliente.getCarrito().confirmarPedido(cliente);
                    cliente.getListaPedido().add(pedido);
                    pedido.imprimirPedido(cliente);
                    break;
                case 5: 
                    cliente.getCarrito().vaciarCarrito();
                    break;
                case 6: 
                    cliente.getCarrito().mostrarCarrito();
                    break;
                case 7: 
                    if(cliente.getListaPedido().isEmpty()){
                        System.out.println("No hay pedidos aún.");
                        break;
                    }
                    ArrayList<Pedido> pedidos = cliente.getListaPedido();
                    System.out.println("Pedidos realizados");
                    for(int i=0; i<pedidos.size(); i++){
                        System.out.print(i+1);
                        System.out.println(pedidos.get(i).getFechaPedido());
                        System.out.println("Productos: ");
                        int j=1;
                        for(Producto p: cliente.getListaPedido().get(i).getListaProductos().values()){
                            System.out.println("   "+ (j) + " "+ p.getNombre());
                        }
                    }
                    int opt;
                    do{
                        System.out.println("¿Qué pedido quieres imprimir? Dame el número");
                        if(!lector.hasNextInt()){
                            System.out.println("Ingresa un número.");
                            lector.next();
                        }
                        opt = lector.nextInt();
                    }while(opt > cliente.getListaPedido().size() || opt <= 0);
                    opt = lector.nextInt();
                    cliente.getListaPedido().get(opt-1).imprimirPedido(cliente);
                    break;
                case 8: 
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        
        }
        
    }
    
    public void menuAdmin(){
    Admin admin = (Admin) this.usuario;        
    int op;
        while(true){
            op = LeerDatos.pedirEntero("==Administrador==\n1)Alta de productos\n2)Modificación de precios\n3)Salir");
            switch (op) {
                case 1: {
                    System.out.print("Ingresa el nombre del producto:");
                    String nombre = lector.nextLine();
                    float precio = LeerDatos.pedirFlotante("Ingresa el precio: ");
                    
                    if (precio <= 0) {
                        System.out.println("El precio debe ser mayor a 0");
                        break;
                    }
                     
                    System.out.print("Ingresa el id: ");
                    String id = lector.nextLine();
                    System.out.print("Ingresa la categoría: ");
                    String categoria = lector.nextLine();
                    
                    Producto producto = new Producto.Builder()
                            .setNombre(nombre)
                            .setPrecio(precio)
                            .setId(id)
                            .setCategoria(categoria)
                            .build();
                    
                     if (admin.agregarProducto(baseDatos, producto)) {
                        System.out.println("Producto agregado.");
                    }
                            
                    break;
                    }
                case 2: {
                    baseDatos.imprimirProductos();
                    System.out.println("Ingresa el id del producto a modificar");
                    String id = lector.nextLine();
                    float precio = LeerDatos.pedirFlotante("Ingresa el nuevo precio: ");
                    
                    admin.cambiarPrecio(baseDatos, id, precio);
                    break;
                }
                case 3: 
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        
        }
    }
}

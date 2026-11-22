package datos;
import usuarios.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import usuarios.Cliente;
import usuarios.Domicilio;

/**
 *
 * @author Sánchez Huerta Donovan Tupac
 */

public class Carrito {
    private HashMap<String, Producto> listaProductos;
    private HashMap<Producto, Integer> cantidades;

    public Carrito() {
        this.listaProductos = new HashMap<>();
        this.cantidades = new HashMap<>();
    }
    
    public void agregarProducto(Producto producto){
        this.listaProductos.put(producto.getId(), producto);
        this.cantidades.merge(producto, 1, Integer::sum);
    }
    
    // Imprime los productos, si hay repetidos, solo los muestra una vez, con la cantidad en carrito
    public void mostrarCarrito(){
        for(Producto p: this.listaProductos.values()){
            System.out.println("Producto "+ p.getId() +": "+ p.getNombre());
            System.out.println("Categoría: "+ p.getCategoria());
            System.out.println("Precio: $"+ (p.getPrecio()*this.cantidades.get(p)));
            System.out.println("Cantidad: "+ this.cantidades.get(p));
        }
    }
    
    public void vaciarCarrito(){
        this.listaProductos.clear();
    }
    public void eliminarProducto(String id){
        Producto producto = this.listaProductos.get(id);
        if(this.cantidades.get(producto) == 1){
            this.listaProductos.remove(id);
            this.cantidades.remove(producto);
        }
        else{
            Scanner scan = new Scanner(System.in);
            char confirmacion = 'n';
            System.out.println("Tienes "+ this.cantidades.get(producto) + " " +this.listaProductos.get(id).getNombre());
            System.out.println("¿Quiéres eliminar todos?");
            confirmacion = scan.next().charAt(0);
            if(confirmacion == 's' || confirmacion == 'S'){
                this.listaProductos.remove(id);
                this.cantidades.remove(producto);
            }
            else{
                int e = -1;
                do{
                    System.out.println("¿Cuántos quieres eliminar");
                    if(!scan.hasNextInt()){
                        System.out.println("Ingresa un número");
                        scan.next();
                    }
                    e = scan.nextInt();
                }while(e > this.cantidades.get(producto) || e <= -1);
                
                
                for(int i=0; i<e; i++){
                    this.cantidades.put(producto, this.cantidades.get(producto)-1);
                    System.out.println("Producto eliminado");
                }
                if(this.cantidades.get(producto) == 0){
                    this.cantidades.remove(producto);
                    this.listaProductos.remove(id);
                }
            }
        }
    }
    
    public Pedido confirmarPedido(Cliente cliente){
        System.out.println("Productos: ");
        mostrarCarrito();
        Scanner scan = new Scanner(System.in);
        char confirmacion = 'n';
        do{
            System.out.println("¿Quiéres eliminar algún producto?");
            confirmacion = scan.next().charAt(0);
            scan.nextLine();
            if(confirmacion == 's' || confirmacion == 'S'){
                String id = "";
                do{
                    System.out.println("¿Qué producto quieres eliminar? Dame el id");
                    id = scan.nextLine();
                }while(!this.listaProductos.containsKey(id));
                eliminarProducto(id);
            }
        }while(confirmacion == 's' || confirmacion == 'S');
        if(this.listaProductos.isEmpty()){
            System.out.println("Haz eliminado todos los productos");
            return null;
        }
        else{
            if(cliente.getListaDomicilios().isEmpty()){
                System.out.println("No hay domicilios registrados");
                return null;
            }
            System.out.println("Domicilios:");
            for(int i = 0; i<cliente.getListaDomicilios().size(); i++){
                System.out.println((i+1));
                cliente.getListaDomicilios().get(i).mostrarDomicilio();
            }
            int opcion1 = -1;
            do{
                System.out.println("¿En qué domicilio quieres que se haga la entrega? Dame el número");
                if(!scan.hasNextInt()){
                    System.out.println("Ingresa un número.");
                    scan.next();
                }
                opcion1 = scan.nextInt();
            }while(opcion1 <= 0 || opcion1 > cliente.getListaDomicilios().size());                                
            
            if(cliente.getListaMediosPago().isEmpty()){
                System.out.println("No hay medios de pago registrados");
                return null;
            }
            System.out.println("Métodos de pago:");
            for(int i = 0; i<cliente.getListaMediosPago().size(); i++){
                System.out.println((i+1));
                System.out.println(cliente.getListaMediosPago().get(i).getAlias());
            }
            int opcion2 = -1;
            do{
                System.out.println("¿Con qué método quieres realizar el pago? Dame el número");
                if(!scan.hasNextInt()){
                    System.out.println("Ingresa un número.");
                    scan.next();
                }
                opcion2 = scan.nextInt();
            }while(opcion2 <= 0 || opcion1 > cliente.getListaMediosPago().size());  
            System.out.println("-----");
            System.out.println("Información del pedido: ");
            System.out.println("Cliente: "+ cliente.getUser());
            System.out.println("Domicilio: " );
            System.out.println("Calle: "+ cliente.getListaDomicilios().get(opcion1 -1).getCalle());
            System.out.println("Colonia: "+ cliente.getListaDomicilios().get(opcion1 -1).getColonia());
            System.out.println("Número de teléfono: "+ cliente.getListaDomicilios().get(opcion1 -1).getNumeroDeTelefono());
            System.out.println("Medio de pago: "+ cliente.getListaMediosPago().get(opcion2 -1).getAlias());
            confirmacion = 'n';
            System.out.println("¿Confirmar pedido?");
            confirmacion = scan.next().charAt(0);
            if(confirmacion == 's' || confirmacion == 'S'){
                boolean procesopago = cliente.getListaMediosPago().get(opcion2-1).confirmarPago();
                if(procesopago){
                    System.out.println("Se ha realizado el pedido. Se generó el tiket.");
                    Pedido nuevo = new Pedido(cliente.getListaDomicilios().get(opcion1 -1), cliente.getListaMediosPago().get(opcion2 -1), this.cantidades, this.listaProductos);
                    vaciarCarrito();
                    return nuevo;
                }
                else{
                    System.out.println("Pago rechazado");
                    return null;
                }
            }
            else{
                return null;
            }
        }
    }

    public HashMap<String, Producto> getListaProductos() {
        return listaProductos;
    }
}

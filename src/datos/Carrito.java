package datos;
import usuarios.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Sánchez Huerta Donovan Tupac
 */

public class Carrito {
    
    private HashMap<Integer, Producto> listaProductos;

    public Carrito() {
        this.listaProductos = new HashMap<>();
    }
    
    public void agregarProducto(Producto producto){
        this.listaProductos.put(this.listaProductos.size() +1, producto);
    }
    
    // Imprime los productos, si hay repetidos, solo los muestra una vez, con la cantidad en carrito
    public void mostrarCarrito(){
        int p;
        for(int i=1; i<=this.listaProductos.size(); i++){
            Producto nuevo = this.listaProductos.get(i);
            p = 0;
            while(this.listaProductos.get(i+1).equals(nuevo)){
                i++;
                p++;
            }
            System.out.println("Producto "+ this.listaProductos.get(i).getId() +": "+ this.listaProductos.get(i).getNombre());
            System.out.println("Categoría: "+ this.listaProductos.get(i).getCategoria());
            System.out.println("Precio: $"+ this.listaProductos.get(i).getPrecio());
            System.out.println("Cantidad: "+ p);
        }
    }
    
    public void vaciarCarrito(){
        this.listaProductos.clear();
    }
    public void eliminarProducto(int pr){
        int p = 0;
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < this.listaProductos.size(); i++) {
            if(this.listaProductos.get(i).equals(this.listaProductos.get(pr -1))){
                p ++;
                indices.add(i);
            }
        }
        if(p == 1){
            this.listaProductos.remove(this.listaProductos.get(indices.get(0)));
        }
        else{
            Scanner scan = new Scanner(System.in);
            char confirmacion = 'n';
            System.out.println("Tienes "+ p + " " +this.listaProductos.get(pr-1).getNombre());
            System.out.println("¿Quiéres eliminar todos?");
            confirmacion = scan.next().charAt(0);
            if(confirmacion == 's' || confirmacion == 'S'){
                for(int i = 0; i<indices.size(); i++){
                    this.listaProductos.remove(indices.get(i));
                    System.out.println("Producto eliminado");
                }
            }
            else{
                int e;
                do{
                    System.out.println("¿Cuántos quieres eliminar");
                }while(!scan.hasNextInt() || scan.nextInt() > p || scan.nextInt() <= -1);
                
                e = scan.nextInt();
                int x=0;
                for(int i=0; i<e; i++){
                    this.listaProductos.remove(indices.get(x++));
                    System.out.println("Producto eliminado");
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
            if(confirmacion == 's' || confirmacion == 'S'){
                int p;
                do{
                    System.out.println("¿Qué producto quieres eliminar? Dame el número");
                }while(!scan.hasNextInt() || scan.nextInt() > this.listaProductos.size() || scan.nextInt() <= 0);
                p = scan.nextInt();
                eliminarProducto(p);
            }
        }while(confirmacion == 's' || confirmacion == 'S');
        if(this.listaProductos.isEmpty()){
            System.out.println("Haz eliminado todos los productos");
            return null;
        }
        else{
            System.out.println("Domicilios:");
            for(int i = 0; i<cliente.getListaDeDomicilios().size(); i++){
                System.out.println((i+1));
                cliente.getListaDeDomicilios().get(i).mostrarDomicilio();
            }
            int opcion1;
            do{
                System.out.println("¿En qué domicilio quieres que se haga la entrega? Dame el número");
            }while(!scan.hasNextInt() || scan.nextInt() > cliente.getListaMediosPago().size() || scan.nextInt() <= 0);                
            opcion1 = scan.nextInt();
            
            System.out.println("Métodos de pago:");
            for(int i = 0; i<cliente.getListaMediosPago().size(); i++){
                System.out.println((i+1));
                cliente.getListaMediosPago().get(i).getAlias();
            }
            int opcion2;
            do{
                System.out.println("¿Con qué medio quieres realizar el pago?");
            }while(!scan.hasNextInt() || scan.nextInt() > cliente.getListaMediosPago().size() || scan.nextInt() <= 0);                
            opcion2 = scan.nextInt();
            
            return new Pedido(cliente.getListaDomicilios().get(opcion1 -1), cliente.getListaMedio.get(opcion2 -1));
        }
    }
}

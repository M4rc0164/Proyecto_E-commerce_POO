package datos;

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
        for(int i=1; i<=this.listaProductos.size(); i++){
            Producto nuevo = this.listaProductos.get(i);
            while(this.listaProductos.get(i+1).equals(nuevo)){
                i++;
            }
            System.out.println("Producto "+ this.listaProductos.get(i).getId() +": "+ this.listaProductos.get(i).getNombre());
            System.out.println("Categoría: "+ this.listaProductos.get(i).getCategoria());
            System.out.println("Precio: $"+ this.listaProductos.get(i).getPrecio());
        }
    }
    
    public void vaciarCarrito(){
        this.listaProductos.clear();
    }
}

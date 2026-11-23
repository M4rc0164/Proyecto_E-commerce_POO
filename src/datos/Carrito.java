package datos;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Sánchez Huerta Donovan Tupac
 */

public class Carrito {
    
    /* Se cambió de HashMap<Integer, Producto> a Map<Producto, Integer> (LinkedHashMap) para agrupar cantidades por producto,
     mantener el orden de inserción y evitar desfases de índices*/
    private Map<Producto, Integer> listaProductos;

    public Carrito() {
        this.listaProductos = new LinkedHashMap<>();
    }
    
    // Agregado para validar la precondición de carrito no vacío antes de confirmar pedido
    public boolean estaVacio() {
        return this.listaProductos.isEmpty();
    }
    
    public void agregarProducto(Producto producto) {
        agregarProducto(producto, 1);
    }

    // Sobrecarga agregada para soportar la cantidad de unidades (cantidad > 0)
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            return;
        }
        int cantidadActual = this.listaProductos.getOrDefault(producto, 0);
        this.listaProductos.put(producto, cantidadActual + cantidad);
    }
    
    // Imprime los productos, si hay repetidos, solo los muestra una vez, con la cantidad en carrito
    // Corregido para manejar carrito vacío
    public void mostrarCarrito() {
        if (this.listaProductos.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        float total = 0;
        for (Map.Entry<Producto, Integer> entry : this.listaProductos.entrySet()) {
            Producto prod = entry.getKey();
            int cantidad = entry.getValue();
            total += prod.getPrecio() * cantidad;
            System.out.println("***");
            System.out.println("Producto " + prod.getId() + ": " + prod.getNombre());
            System.out.println("Categoría: " + prod.getCategoria());
            System.out.println("Precio: $" + prod.getPrecio());
            System.out.println("Cantidad: " + cantidad);
        }
        System.out.println("Total estimado: $" + total);
    }
    
    // Devuelve una copia para que vaciarCarrito() no borre los productos de los pedidos ya confirmados
    public Map<Producto, Integer> obtenerCopiaProductos() {
        return new LinkedHashMap<>(this.listaProductos);
    }
    
    public void vaciarCarrito() {
        this.listaProductos.clear();
    }
}

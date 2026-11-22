package datos;
import Pagos.*;
import usuarios.Domicilio;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import usuarios.*;

/**
 *
 * @author Sánchez Huerta Donovan Tupac
 */

public class Pedido {
    
    // Se cambió a Map<Producto, Integer>  para conservar cantidades y evitar que vaciar el carrito altere el pedido
    private Map<Producto, Integer> listaProductos;
    private LocalDate fechaPedido;
    private float monto = 0;
    private Domicilio domicilio;
    private MedioDePago mediopago;
    private LocalDate fechaEntrega;

    public Pedido(Domicilio domicilio, MedioDePago mediopago, Map<Producto, Integer> listaproductos){
        this.listaProductos = new LinkedHashMap<>(listaproductos);
        this.fechaPedido = LocalDate.now();
        // Se calcula el monto multiplicando precio por cantidad de cada producto (antes iteraba desde índice 0 )
        for (Map.Entry<Producto, Integer> entry : this.listaProductos.entrySet()) {
            this.monto += entry.getKey().getPrecio() * entry.getValue();
        }
        this.domicilio = domicilio;
        this.mediopago = mediopago;
        Random aleatorio = new Random();
        this.fechaEntrega = LocalDate.now().plusDays(aleatorio.nextInt(1,8));
    }

    // Getters y resumen agregados para poder listar el historial de pedidos en la opción "Imprimir ticket de un pedido"
    public float getMonto() {
        return monto;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public String getResumen(int numpedido) {
        return "Pedido " + numpedido + " | Fecha: " + this.fechaPedido
                + " | Total: $" + this.monto + " | Pago: " + this.mediopago.getDescripcionTicket();
    }
    
    public void imprimirPedido(Cliente cliente){
        try{
            int numpedido = cliente.getListaPedido().indexOf(this) + 1;
            // Se crea la carpeta con mkdirs() y se agregó extensión .txt para evitar IOException si el directorio no existe
            File carpeta = new File("Pedidos cliente " + cliente.getUser());
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }
            File ticket = new File(carpeta, "Pedido " + numpedido + ".txt");
            try (FileWriter escritura = new FileWriter(ticket)) {
                escritura.write("Pedido " + numpedido + "\n");
                escritura.write("Cliente: " + cliente.getNombre() + " (" + cliente.getUser() + ")\n");
                escritura.write("Productos:\n");
                // Se imprimen nombre, cantidad, precio unitario y subtotal (antes omitía cantidades e imprimía el nombre en lugar del precio)
                int i = 1;
                for (Map.Entry<Producto, Integer> entry : this.listaProductos.entrySet()) {
                    Producto prod = entry.getKey();
                    int cantidad = entry.getValue();
                    float subtotal = prod.getPrecio() * cantidad;
                    escritura.write(i + ". " + prod.getNombre() + " (ID: " + prod.getId() + ")"
                            + " | Cantidad: " + cantidad
                            + " | Precio unitario: $" + prod.getPrecio()
                            + " | Subtotal: $" + subtotal + "\n");
                    i++;
                }
                escritura.write("Monto total: $" + this.monto + "\n");
                escritura.write("Fecha de pedido: " + this.fechaPedido + "\n");
                escritura.write("Fecha de entrega: " + this.fechaEntrega + "\n");
                escritura.write("Domicilio:\n");
                escritura.write("Calle: " + this.domicilio.getCalle() + "\n");
                escritura.write("Colonia: " + this.domicilio.getColonia() + "\n");
                if (this.domicilio.getNumeroDeTelefono() != null && !this.domicilio.getNumeroDeTelefono().isEmpty()) {
                    escritura.write("Teléfono: " + this.domicilio.getNumeroDeTelefono() + "\n");
                }
                // Se usa  getDescripcionTicket() en vez de if/instanceof y substring que fallaba con el alias
                escritura.write("Medio de pago: " + this.mediopago.getDescripcionTicket() + "\n");
            }
            System.out.println("Ticket guardado en: " + ticket.getPath());
        } catch (IOException ex) {
            System.out.println("Ocurrió un error al generar el ticket: " + ex.getMessage());
        }
    }
}

package datos;

import Pagos.Paypal;
import Pagos.TarjetaDeCredito;
import usuarios.Admin;
import usuarios.Cliente;
import usuarios.Domicilio;
import usuarios.Usuario;
import java.util.List;
import java.util.ArrayList;

/**
 * @author marco
 */
public class BaseDatos {

    private List<Producto> inventario;
    private List<Usuario> usuarios;
    private static BaseDatos baseDatos;

    private BaseDatos() {
        this.inventario = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.cargarDatosIniciales();
    }

    public static BaseDatos getBaseDatos() {
        if (baseDatos == null) {
            baseDatos = new BaseDatos();
        }
        return baseDatos;
    }

    private void cargarDatosIniciales() {
        usuarios.add(new Admin("admin1", "Administrador Principal", "admin123"));
        usuarios.add(new Admin("admin2", "Supervisor Catalogo", "admin456"));

        Cliente cliente1 = new Cliente("juan", "Juan Perez", "1234");
        cliente1.agregarDomicilio(new Domicilio("Av. Universidad 3000", "Copilco", "5512345678"));
        cliente1.agregarMedioPago(new TarjetaDeCredito("Tarjeta", "4152313812345678", "1234"));
        cliente1.agregarMedioPago(new Paypal("juan@correo.com", "pass123", "Paypal"));
        usuarios.add(cliente1);

        Cliente cliente2 = new Cliente("maria", "Maria Lopez", "5678");
        usuarios.add(cliente2);

        inventario.add(new Producto.Builder()
                .setId("P001")
                .setNombre("Laptop Gamer")
                .setCategoria("Electronica")
                .setPrecio(18500.0f)
                .build());
        inventario.add(new Producto.Builder()
                .setId("P002")
                .setNombre("Mouse Inalambrico")
                .setCategoria("Accesorios")
                .setPrecio(450.50f)
                .build());
        inventario.add(new Producto.Builder()
                .setId("P003")
                .setNombre("Teclado Mecanico")
                .setCategoria("Accesorios")
                .setPrecio(1200.0f)
                .build());
        inventario.add(new Producto.Builder()
                .setId("P004")
                .setNombre("Audifonos Bluetooth")
                .setCategoria("Audio")
                .setPrecio(899.99f)
                .build());
    }

    // Método auxiliar para evitar pedir IDs en los menús cuando el catálogo está vacío
    public boolean inventarioVacio() {
        return this.inventario.isEmpty();
    }

    public void agregarProducto(Producto nuevoProducto) {
        boolean seEncuentra = false;
        for (Producto i : inventario) {
            if (i.getId().equals(nuevoProducto.getId())) {
                System.out.println(nuevoProducto.getNombre() + " ya se encuentra en el inventario");
                seEncuentra = true;
                break;
            }
        }
        if (!seEncuentra) {
            inventario.add(nuevoProducto);
        }
    }
    
    public void agregarUsuario(Usuario nuevoUsuario){
        boolean seEncuentra = false;
        for (Usuario i : usuarios) {
            if (i.getUser().equals(nuevoUsuario.getUser())) {
                System.out.println(nuevoUsuario.getUser()+ " ya se encuentra registrado");
                seEncuentra = true;
                break;
            }
        }
        if (!seEncuentra) {
            usuarios.add(nuevoUsuario);
        }
    }

    public Usuario buscarUsuario(String user){
        for(Usuario u: this.usuarios){
            if(u.getUser().equals(user)){
                return u;
            }
        }
        
        return null;
    }
    

    public void imprimirProductos(){
        if(this.inventario.isEmpty()){
            System.out.println("No hay productos registrados");
            return;
        }
        
        for(Producto p : this.inventario){
            System.out.println("***");
            System.out.println("Id: "+p.getId());
            System.out.println("Nombre: "+p.getNombre());
            System.out.println("Precio: "+p.getPrecio());
            System.out.println("Categoría: "+p.getCategoria());  
        }
    }
    
    public Producto buscarProducto(String id){
        for(Producto p : this.inventario){
            if(p.getId().equals(id)){
                return p;
            }
        }
        
        return null;
    }
    
}

package datos;

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
    }

    public static BaseDatos getBaseDatos() {
        if (baseDatos == null) {
            System.out.println("Se crea base de datos");
            baseDatos = new BaseDatos();
        } else {
            System.out.println("Base de datos ya creada");
        }
        return baseDatos;
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
    
    //para imprimir los productos
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
    
    //para buscar los productos 
    public Producto buscarProducto(String id){
        for(Producto p : this.inventario){
            if(p.getId().equals(id)){
                return p;
            }
        }
        
        return null;
    }
    
}

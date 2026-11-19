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
}

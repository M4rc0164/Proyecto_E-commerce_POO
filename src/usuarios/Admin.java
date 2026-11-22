/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

import datos.*;

/**
 *
 * @author EdelM
 */
public class Admin extends Usuario{

    public Admin(String user, String nombre, String password) {
        super(user, nombre, password);
    }
    
    public boolean agregarProducto(BaseDatos baseDatos, Producto producto) {
        if (baseDatos.buscarProducto(producto.getId()) != null) {
            System.out.println("Ya existe un producto con ese id");
            return false;
        }
        baseDatos.agregarProducto(producto);
        return true;
    }

    public boolean cambiarPrecio(BaseDatos baseDatos, String id, float nuevoPrecio) {
        
        
        if (nuevoPrecio <= 0) {
            System.out.println("El precio debe ser mayor a 0");
            return false;
        }
        
        Producto producto = baseDatos.buscarProducto(id);
        if (producto == null) {
            System.out.println("No existe un producto con ese id");
            return false;
        }
        
        producto.setPrecio(nuevoPrecio);
        return true;
    }
}

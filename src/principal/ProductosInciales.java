/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package principal;

import datos.BaseDatos;
import datos.Producto;
import usuarios.Admin;

/**
 *
 * @author donos
 */

public class ProductosInciales {
    
    
    public static Admin admin = new Admin("Administrador", "Sergio", "1234");
    
    public static void agregarProductos(){
        BaseDatos baseDatos = BaseDatos.getBaseDatos();
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Laptop").setPrecio(12500.50f).setCategoria("Electrónica").setId("1001").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Mouse").setPrecio(250.99f).setCategoria("Electrónica").setId("1002").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Teclado").setPrecio(450.50f).setCategoria("Electrónica").setId("1003").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Monitor").setPrecio(3200.00f).setCategoria("Electrónica").setId("1004").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Audífonos").setPrecio(599.99f).setCategoria("Electrónica").setId("1005").build());

        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Cuaderno").setPrecio(45.50f).setCategoria("Papelería").setId("1006").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Lapicero").setPrecio(12.00f).setCategoria("Papelería").setId("1007").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Borrador").setPrecio(8.50f).setCategoria("Papelería").setId("1008").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Sacapuntas").setPrecio(15.00f).setCategoria("Papelería").setId("1009").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Regla").setPrecio(20.00f).setCategoria("Papelería").setId("1010").build());

        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Mochila").setPrecio(850.00f).setCategoria("Accesorios").setId("1011").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Cartera").setPrecio(350.00f).setCategoria("Accesorios").setId("1012").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Reloj").setPrecio(1200.00f).setCategoria("Accesorios").setId("1013").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Gorra").setPrecio(180.00f).setCategoria("Accesorios").setId("1014").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Cinturón").setPrecio(220.00f).setCategoria("Accesorios").setId("1015").build());

        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Camisa").setPrecio(399.99f).setCategoria("Ropa").setId("1016").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Pantalón").setPrecio(650.00f).setCategoria("Ropa").setId("1017").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Sudadera").setPrecio(550.00f).setCategoria("Ropa").setId("1018").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Playera").setPrecio(199.99f).setCategoria("Ropa").setId("1019").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Calcetines").setPrecio(75.00f).setCategoria("Ropa").setId("1020").build());

        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Café").setPrecio(95.00f).setCategoria("Alimentos").setId("1021").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Galletas").setPrecio(28.50f).setCategoria("Alimentos").setId("1022").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Chocolate").setPrecio(35.00f).setCategoria("Alimentos").setId("1023").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Cereal").setPrecio(78.90f).setCategoria("Alimentos").setId("1024").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Jugo").setPrecio(25.00f).setCategoria("Alimentos").setId("1025").build());

        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Jabón").setPrecio(22.00f).setCategoria("Limpieza").setId("1026").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Detergente").setPrecio(115.50f).setCategoria("Limpieza").setId("1027").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Esponja").setPrecio(18.00f).setCategoria("Limpieza").setId("1028").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Cloro").setPrecio(32.00f).setCategoria("Limpieza").setId("1029").build());
        admin.agregarProducto(baseDatos, new Producto.Builder().setNombre("Desinfectante").setPrecio(65.00f).setCategoria("Limpieza").setId("1030").build());
    
    }
    
    
    
    
}

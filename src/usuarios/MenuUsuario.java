/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

import Pagos.*;
import datos.*;
import java.util.ArrayList;
import utilidades.*;

/**
 *
 * @author EdelM
 */
public class MenuUsuario {
    private Usuario usuario;
    private BaseDatos baseDatos;

    public MenuUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.baseDatos = BaseDatos.getBaseDatos();
    }

    // Se mantiene el ciclo while con opción 8) Salir para que el cliente permanezca en su menú tras cada operación
    public void menuCliente(){
        Cliente cliente = (Cliente) this.usuario;
        int op;
        while(true){
            op = LeerDatos.pedirEntero("==Cliente==\n1)Agregar producto al carrito\n2)Registrar domicilio\n3)Registrar medio de Pago\n4)Confirmar pedido\n5)Vaciar carrito\n6)Consultar carrito\n7)Imprimir ticket de un pedido\n8)Salir\nSeleccione una opción: ");
            switch (op) {
            case 1: {
                // Se verifica inventario vacío antes de pedir el ID
                if (this.baseDatos.inventarioVacio()) {
                    System.out.println("No hay productos registrados");
                    break;
                }
                this.baseDatos.imprimirProductos();
                
                String idProd = LeerDatos.pedirTexto("Ingresa el id del producto que se añadirá al carrito: ");
                Producto producto = this.baseDatos.buscarProducto(idProd);
                if(producto == null){
                    System.out.println("No existe un producto con ese id");
                    break;
                }
                // Agregado: solicitar y validar cantidad > 0 (antes siempre agregaba 1 unidad)
                int cantidad = LeerDatos.pedirEntero("Ingresa la cantidad de unidades: ");
                if (cantidad <= 0) {
                    System.out.println("La cantidad debe ser mayor a 0");
                    break;
                }
                
                cliente.getCarrito().agregarProducto(producto, cantidad);
                System.out.println("Producto agregado al carrito.");
                break;
            }
            case 2: {
                String calle = LeerDatos.pedirTexto("Ingresa la calle: ");
                String colonia = LeerDatos.pedirTexto("Ingresa la colonia: ");
                String telefono = LeerDatos.pedirTexto("Ingresa el teléfono: ");
                // Se agregó validación para evitar registrar domicilios con calle o colonia vacías
                if (calle.isEmpty() || colonia.isEmpty()) {
                    System.out.println("La calle y la colonia son obligatorias.");
                    break;
                }
                        
                Domicilio domicilio = new Domicilio(calle, colonia, telefono);

                if (cliente.agregarDomicilio(domicilio)) {
                    System.out.println("Domicilio registrado correctamente.");
                }
                
                break;
            }
            case 3: {
                System.out.println("Registrar medio de pago");
                int tipo = 0;
                
                while (tipo != 1 && tipo != 2) {
                    tipo = LeerDatos.pedirEntero("1) Paypal\n2) Tarjeta de crédito\nSeleccione una opción: ");
                }
                
                // Se valida que los campos no estén vacíos, se asigna alias descriptivo ("Paypal"/"Tarjeta")
                // y se revisa el retorno de agregarMedioPago() para no imprimir éxito cuando es duplicado
                boolean registrado = false;
                if (tipo == 1) {
                    String correo = LeerDatos.pedirTexto("Correo electrónico: ");
                    String pass = LeerDatos.pedirTexto("Contraseña: ");
                    if (correo.isEmpty() || pass.isEmpty()) {
                        System.out.println("Correo y contraseña no pueden estar vacíos.");
                        break;
                    }
                    registrado = cliente.agregarMedioPago(new Paypal(correo, pass, "Paypal"));
                    
                } else {
                    String numero = LeerDatos.pedirTexto("Número de tarjeta: ");
                    String nip = LeerDatos.pedirTexto("NIP: ");
                    if (numero.isEmpty() || nip.isEmpty()) {
                        System.out.println("Número de tarjeta y NIP no pueden estar vacíos.");
                        break;
                    }
                    registrado = cliente.agregarMedioPago(new TarjetaDeCredito("Tarjeta", numero, nip));
                    
                }
                if (registrado) {
                    System.out.println("Medio de pago registrado.");
                }
                break;
            }
            case 4: {
                // el case 4 estaba vacío
                confirmarPedido(cliente);
                break;
            }
            case 5: {
                cliente.getCarrito().vaciarCarrito();
                // Se agregó mensaje de confirmación al usuario
                System.out.println("Carrito vaciado correctamente.");
                break;
            }
            case 6: {
                cliente.getCarrito().mostrarCarrito();
                break;
            }
            case 7: {
                // el case 7 estaba vacío
                imprimirTicketHistorico(cliente);
                break;
            }
            case 8: // Permite cerrar sesión y regresar al menú principal cuando el cliente lo indique
                return;
            default:
                System.out.println("Opción incorrecta");
            }
        }
    }

    // Método agregado valida las 3 precondiciones (carrito, domicilio y medio de pago),
    // autentica con confirmarPago(), guarda el pedido, genera el ticket .txt y vacía el carrito
    private void confirmarPedido(Cliente cliente) {
        if (cliente.getCarrito().estaVacio()) {
            System.out.println("No se puede confirmar el pedido: el carrito está vacío.");
            return;
        }
        ArrayList<Domicilio> domicilios = cliente.getListaDomicilios();
        if (domicilios.isEmpty()) {
            System.out.println("No se puede confirmar el pedido: debes registrar al menos un domicilio.");
            return;
        }
        ArrayList<MedioDePago> mediosPago = cliente.getListaMediosPago();
        if (mediosPago.isEmpty()) {
            System.out.println("No se puede confirmar el pedido: debes registrar al menos un medio de pago.");
            return;
        }

        System.out.println("Domicilios registrados:");
        for (int i = 0; i < domicilios.size(); i++) {
            System.out.println((i + 1) + ") Calle: " + domicilios.get(i).getCalle() + ", Colonia: " + domicilios.get(i).getColonia());
        }
        int opDom = LeerDatos.pedirEntero("Selecciona el número de domicilio de envío: ") - 1;
        if (opDom < 0 || opDom >= domicilios.size()) {
            System.out.println("Selección de domicilio inválida.");
            return;
        }

        System.out.println("Medios de pago registrados:");
        for (int i = 0; i < mediosPago.size(); i++) {
            System.out.println((i + 1) + ") " + mediosPago.get(i).getDescripcionTicket());
        }
        int opPago = LeerDatos.pedirEntero("Selecciona el número de medio de pago: ") - 1;
        if (opPago < 0 || opPago >= mediosPago.size()) {
            System.out.println("Selección de medio de pago inválida.");
            return;
        }

        MedioDePago medioSeleccionado = mediosPago.get(opPago);
        if (!medioSeleccionado.confirmarPago()) {
            System.out.println("Pago rechazado. El pedido no fue confirmado.");
            return;
        }

        Pedido pedido = new Pedido(
                domicilios.get(opDom),
                medioSeleccionado,
                cliente.getCarrito().obtenerCopiaProductos()
        );
        cliente.getListaPedido().add(pedido);
        pedido.imprimirPedido(cliente);
        cliente.getCarrito().vaciarCarrito();
        System.out.println("Pedido confirmado correctamente. El carrito se ha vaciado.");
    }

    // Método agregado que muestra pedidos previos y reimprime el ticket .txt del pedido seleccionado
    private void imprimirTicketHistorico(Cliente cliente) {
        ArrayList<Pedido> pedidos = cliente.getListaPedido();
        if (pedidos.isEmpty()) {
            System.out.println("No tienes pedidos confirmados.");
            return;
        }
        System.out.println("Pedidos confirmados:");
        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println((i + 1) + ") " + pedidos.get(i).getResumen(i + 1));
        }
        int opPedido = LeerDatos.pedirEntero("Selecciona el número de pedido para reimprimir su ticket: ") - 1;
        if (opPedido < 0 || opPedido >= pedidos.size()) {
            System.out.println("Selección de pedido inválida.");
            return;
        }
        pedidos.get(opPedido).imprimirPedido(cliente);
    }
    
    // Se mantiene el ciclo while con opción 3) Salir para que el administrador permanezca en su menú tras cada operación
    public void menuAdmin(){
        Admin admin = (Admin) this.usuario;        
        int op;
        while(true){
            op = LeerDatos.pedirEntero("==Administrador==\n1)Alta de productos\n2)Modificación de precios\n3)Salir\nSeleccione una opción: ");
            switch (op) {
            case 1: {
                String id = LeerDatos.pedirTexto("Ingresa el id: ");
                String nombre = LeerDatos.pedirTexto("Ingresa el nombre del producto: ");
                String categoria = LeerDatos.pedirTexto("Ingresa la categoría: ");
                float precio = LeerDatos.pedirFlotante("Ingresa el precio: ");
                
                // Se agregó validación de campos obligatorios no vacíos
                if (id.isEmpty() || nombre.isEmpty() || categoria.isEmpty()) {
                    System.out.println("ID, nombre y categoría son obligatorios.");
                    break;
                }
                if (precio <= 0) {
                    System.out.println("El precio debe ser mayor a 0");
                    break;
                }
                
                Producto producto = new Producto.Builder()
                        .setNombre(nombre)
                        .setPrecio(precio)
                        .setId(id)
                        .setCategoria(categoria)
                        .build();
                
                if (admin.agregarProducto(baseDatos, producto)) {
                    System.out.println("Producto agregado.");
                }
                        
                break;
            }
            case 2: {
                // Se verifica inventario vacío antes de solicitar ID y se evalúa el retorno de cambiarPrecio() para confirmar el cambio
                if (baseDatos.inventarioVacio()) {
                    System.out.println("No hay productos registrados");
                    break;
                }
                baseDatos.imprimirProductos();
                String id = LeerDatos.pedirTexto("Ingresa el id del producto a modificar: ");
                float precio = LeerDatos.pedirFlotante("Ingresa el nuevo precio: ");
                
                if (admin.cambiarPrecio(baseDatos, id, precio)) {
                    System.out.println("Precio modificado correctamente.");
                }
                break;
            }
            case 3: // Permite cerrar sesión y regresar al menú principal cuando el administrador lo indique
                return;
            default:
                System.out.println("Opción incorrecta");
            }
        }
    }
}

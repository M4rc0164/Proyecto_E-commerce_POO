/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

import Pagos.*;
import datos.*;
import java.util.ArrayList;

/**
 *
 * @author EdelM
 */
public class Cliente extends Usuario{
    ArrayList<Domicilio> listaDomicilios;
    ArrayList<Pedido> listaPedido;
    ArrayList<MedioDePago> listaMediosPago;
    
    Carrito carrito;
    
    public Cliente(String user, String nombre, String password) {
        super(user, nombre, password);
        this.listaDomicilios = new ArrayList<>();
        this.listaPedido = new ArrayList<>();
        this.listaMediosPago = new ArrayList<>();
        this.carrito = new Carrito();
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public ArrayList<Domicilio> getListaDomicilios() {
        return listaDomicilios;
    }

    public ArrayList<Pedido> getListaPedido() {
        return listaPedido;
    }

    public ArrayList<MedioDePago> getListaMediosPago() {
        return listaMediosPago;
    }
    
    public boolean agregarDomicilio(Domicilio domicilio) {
        if (listaDomicilios.contains(domicilio)) {
            System.out.println("Ese domicilio ya está registrado.");
            return false;
        }
        listaDomicilios.add(domicilio);
        return true;
    }
    
    public boolean agregarMedioPago(MedioDePago medioPago) {
        if (listaMediosPago.contains(medioPago)) {
            System.out.println("Ese medio de pago ya está registrado.");
            return false;
        }
        listaMediosPago.add(medioPago);
        return true;
    }
    
    
}

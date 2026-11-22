/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;
import Pagos.*;
import java.util.ArrayList;
import Pagos.*;
import datos.*;
import java.util.ArrayList;

/**
 *
 * @author EdelM
 */
public class Cliente extends Usuario{
    //Todas las listas registraran datos de un usuario a la vez
    ArrayList<Domicilio> listaDeDomicilios=new ArrayList<>();
    //Por ejemplo, en la lista de domicilios se regitran todos los domicilios registrados de un usuario
    ArrayList<MedioDePago> mediosDePago=new ArrayList<>();
    ArrayList <Pedido> listaDePedidos=new ArrayList<>();
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

    public ArrayList<Domicilio> getListaDeDomicilios() {
        return listaDeDomicilios;
    }

    public void setListaDeDomicilios(ArrayList<Domicilio> listaDeDomicilios) {
        this.listaDeDomicilios = listaDeDomicilios;
    }

    public ArrayList<MedioDePago> getMediosDePago() {
        return mediosDePago;
    }

    public void setMediosDePago(ArrayList<MedioDePago> mediosDePago) {
        this.mediosDePago = mediosDePago;
    }

    public ArrayList<Pedido> getListaDePedidos() {
        return listaDePedidos;
    }

    public void setListaDePedidos(ArrayList<Pedido> listaDePedidos) {
        this.listaDePedidos = listaDePedidos;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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

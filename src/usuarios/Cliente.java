/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;
import Pagos.*;
import java.util.ArrayList;
import datos.*;

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
    Carrito carrito;
    
    public Cliente(String user, String nombre, String password) {
        super(user, nombre, password);
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
    
}

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
public class Cliente extends Usuario{
    ArrayLisy<Domicilio> listaDomicilios;
    
    Carrito carrito;
    
    public Cliente(String user, String nombre, String password) {
        super(user, nombre, password);
    }

    public Carrito getCarrito() {
        return carrito;
    }
    
    
}

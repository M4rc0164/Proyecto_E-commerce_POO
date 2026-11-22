package datos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;
import usuarios.*;
import Pagos.*;

/**
 *
 * @author Sánchez Huerta Donovan Tupac
 */

public class Pedido {
    
    private HashMap<Integer, Producto> listaProductos;
    private LocalDate fechaPedido;
    private float monto = 0;
    private Domicilio domicilio;
    private MedioDePago mediopago;
    private LocalDate fechaEntrega;
    
    public Pedido(Domicilio domicilio, MedioDePago mediopago, HashMap<Integer,Producto> listaproductos){
        this.listaProductos = listaproductos;
        this.fechaPedido = LocalDate.now();
        for(int i=0; i<listaproductos.size(); i++){
            this.monto += listaproductos.get(i).getPrecio();
        }
        this.domicilio = domicilio;
        this.mediopago = mediopago;
        Random aleatorio = new Random();
        this.fechaEntrega = LocalDate.now().plusDays(aleatorio.nextInt(1,8));
    }
    
    public void imprimirPedido(Cliente cliente){
        try {
            int numpedido = cliente.getListaPedido().indexOf(this) + 1;
            File ticket = new File("Pedidos cliente"+ cliente.getNombre() + "//Pedido "+ numpedido);
            ticket.createNewFile();
            try (FileWriter escritura = new FileWriter("Pedidos cliente"+ cliente.getNombre() + "//Pedido "+ numpedido)) {
                escritura.write("Pedido "+ numpedido + "\n");
                escritura.write("Productos: \n");
                for(int i=0; i<this.listaProductos.size(); i++){
                    //Considerando nombres muy largos
                    if(this.listaProductos.get(i).getNombre().length() > 30){
                        //Considerando si el nombre tiene espacios
                        if(this.listaProductos.get(i).getNombre().indexOf(" ") != - 1){
                            int indice = this.listaProductos.get(i).getNombre().indexOf(" ");
                            int tamaño = 30 - this.listaProductos.get(i).getNombre().substring(0, indice +1).length();
                            escritura.write((i+1) + ". " +this.listaProductos.get(i).getNombre().substring(0, indice +1));
                            for(int j = 0; j<tamaño; j++){
                                escritura.write(" ");
                            }
                            escritura.write(" $ "+ this.listaProductos.get(i).getPrecio()+ "\n");
                            escritura.write("   "+this.listaProductos.get(i).getNombre().substring(indice +1, this.listaProductos.get(i).getNombre().length()) + "\n");
                        }
                        else{
                            escritura.write((i+1)+ ". "+this.listaProductos.get(i).getNombre().substring(0, 31) + " $" +this.listaProductos.get(i).getPrecio()+ "\n");
                            escritura.write("   "+ this.listaProductos.get(i).getNombre().substring(31, this.listaProductos.get(i).getNombre().length()) + "\n");
                        }
                    }
                    else{
                        escritura.write((i+1)+ ". " + this.listaProductos.get(i).getNombre()+ " $" + this.listaProductos.get(i).getNombre()+ "\n");
                    }
                }
                escritura.write("Monto total                       $"+ this.monto+ "\n");
                escritura.write("Fecha de pedido: "+ this.fechaPedido+ "\n");
                escritura.write("Fecha de entrega: "+ this.fechaEntrega+ "\n");
                escritura.write("Domicilio: \n");
                escritura.write("Calle: "+ this.domicilio.getCalle()+ "\n");
                escritura.write("Colonia: "+ this.domicilio.getColonia()+ "\n");
                escritura.write("Teléfono: "+ this.domicilio.getNumeroDeTelefono()+ "\n");
                escritura.write("Medio de pago: "+ this.mediopago.getAlias()+ "\n");
                if(this.mediopago instanceof TarjetaDeCredito tarjeta){
                    escritura.write("**** **** **** "+ tarjeta.getNumeroDeTarjetaDeCredito().substring(16,tarjeta.getNumeroDeTarjetaDeCredito().length())+ "\n");
                }
                else if(this.mediopago instanceof Paypal paypal){
                    escritura.write("Correo: "+ paypal.getCorreoElectronico() + "\n");
                }
            }
        } catch (IOException ex) {
            System.out.println("Ocurrió un error");
        }
    }
}

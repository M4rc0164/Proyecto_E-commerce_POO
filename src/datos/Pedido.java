package datos;
import Pagos.*;
import usuarios.Domicilio;
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
    
    private HashMap<Producto, Integer> cantidades;
    private HashMap<String, Producto> listaproductos;
    private LocalDate fechaPedido;
    private float monto = 0;
    private Domicilio domicilio;
    private MedioDePago mediopago;
    private LocalDate fechaEntrega;
    public Pedido(Domicilio domicilio, MedioDePago mediopago, HashMap<Producto,Integer> cantidades, HashMap<String, Producto> listaproductos){
        this.listaproductos = listaproductos;
        this.cantidades = cantidades;
        this.fechaPedido = LocalDate.now();
        for(Producto p: listaproductos.values()){
            for(int i=0; i<cantidades.get(p); i++){
                this.monto += p.getPrecio();
            }
        }
        this.domicilio = domicilio;
        this.mediopago = mediopago;
        Random aleatorio = new Random();
        this.fechaEntrega = LocalDate.now().plusDays(aleatorio.nextInt(1,8));
    }
    
    public void imprimirPedido(Cliente cliente){
        File carpeta = new File("Pedidos cliente"+ cliente.getNombre());
        carpeta.mkdir();
        try{
            int numpedido;
            if(cliente.getListaPedido().indexOf(this) == -1){
                numpedido = cliente.getListaPedido().size() +1;
            }
            else{
                numpedido = cliente.getListaPedido().indexOf(this) + 1; 
            }
            File ticket = new File("Pedidos cliente"+ cliente.getNombre() + "//Pedido "+ numpedido);
            ticket.createNewFile();
            try (FileWriter escritura = new FileWriter("Pedidos cliente"+ cliente.getNombre() + "//Pedido "+ numpedido)) {
                escritura.write("Pedido "+ numpedido + "\n");
                escritura.write("Productos: \n");
                int i=0;
                for(Producto p: cliente.getListaPedido().get(cliente.getListaPedido().indexOf(this)).listaproductos.values()){
                    //Considerando nombres muy largos
                    if(p.getNombre().length() > 30){
                        //Considerando si el nombre tiene espacios
                        if(p.getNombre().indexOf(" ") != - 1){
                            int indice = p.getNombre().indexOf(" ");
                            int tamaño = 30 - p.getNombre().substring(0, indice +1).length();
                            escritura.write((i+1) + ". " +p.getNombre().substring(0, indice +1));
                            for(int j = 0; j<tamaño; j++){
                                escritura.write(" ");
                            }
                            escritura.write(" $ "+ p.getPrecio()+ "\n");
                            escritura.write("   "+p.getNombre().substring(indice +1, p.getNombre().length()) + "\n");
                        }
                        else{
                            escritura.write((i+1)+ ". "+p.getNombre().substring(0, 31) + " $" +p.getPrecio()+ "\n");
                            escritura.write("   "+ p.getNombre().substring(31, p.getNombre().length()) + "\n");
                        }
                    }
                    else{
                        int tamaño = 30 - p.getNombre().length();
                        escritura.write((i+1)+ ". " + p.getNombre());
                        for(int j=0; j<=tamaño; j++){
                            escritura.write(" ");
                        }
                        escritura.write("$" + p.getPrecio()+ "\n");   
                    }
                    i++;
                }
            escritura.write("Monto total                       $"+ this.monto+ "\n");
            escritura.write("Fecha de pedido: "+ this.fechaPedido+ "\n");
            escritura.write("Fecha de entrega: "+ this.fechaEntrega+ "\n");
            escritura.write("Domicilio: \n");
            escritura.write("Calle: "+ this.domicilio.getCalle()+ "\n");
            escritura.write("Colonia: "+ this.domicilio.getColonia()+ "\n");
            escritura.write("Teléfono: "+ this.domicilio.getNumeroDeTelefono()+ "\n");
            escritura.write("Medio de pago: "+ this.mediopago.getAlias()+ "\n");
            if(this.mediopago.getAlias().equals("Tarjeta")){
                escritura.write("**** **** **** "+ this.mediopago.getAlias().substring(16,this.mediopago.getAlias().length())+ "\n");
            }
            else if(this.mediopago instanceof Paypal paypal){
                    escritura.write("Correo: "+ paypal.getCorreoElectronico()+ "\n");
            }
            
            escritura.close();
        }
        } catch (IOException ex) {
            System.out.println("Ocurrió un error");
        }
    }

    public HashMap<String, Producto> getListaProductos() {
        return this.listaproductos;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }
   
}

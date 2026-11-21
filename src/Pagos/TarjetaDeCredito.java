package Pagos;
import java.util.Scanner;
/**
 *
 * @author Velazquez De Jesus Dante Yabe
 */
public class TarjetaDeCredito extends MedioDePago{
    private String numeroDeTarjetaDeCredito;
    private String NIP;

    public TarjetaDeCredito(String alias, String numeroDeTarjetaDeCredito, String NIP) {
        this.numeroDeTarjetaDeCredito=numeroDeTarjetaDeCredito;
        this.NIP=NIP;
        super(alias);
    }

    public String getNumeroDeTarjetaDeCredito() {
        return numeroDeTarjetaDeCredito;
    }

    public void setNumeroDeTarjetaDeCredito(String numeroDeTarjetaDeCredito) {
        this.numeroDeTarjetaDeCredito = numeroDeTarjetaDeCredito;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    @Override
    public void confirmarPago() {
        Scanner lector=new Scanner(System.in);
        System.out.println("Confirmando pago");
        if(this.numeroDeTarjetaDeCredito!=null&&this.NIP!=null&&this.getAlias()!=null){
            System.out.println("Favor de confirmar el NIP de su tarjeta de crédito");
            String confirmacionDeNIP=lector.next();
            while(!this.NIP.equals(confirmacionDeNIP)){
                System.out.println("Favor de ingresar de nuevo el NIP, no coincide con el ingresado en su registro");
            }
        }else{
            System.out.println("Faltan datos para realizar la compra");
        }
    }
    
    
}

package Pagos;
import java.util.Scanner;
/**
 *
 * @author Velazquez De Jesus Dante Yabe
 */
public class TarjetaDeCredito extends MedioDePago{
    private boolean confirmacion=false;
    private String numeroDeTarjetaDeCredito;
    private String NIP;

    public TarjetaDeCredito(String alias, String numeroDeTarjetaDeCredito, String NIP) {
        super(alias);
        this.numeroDeTarjetaDeCredito=numeroDeTarjetaDeCredito;
        this.NIP=NIP;
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
    public boolean confirmarPago() {
        if(this.confirmacion==true){
            System.out.println("El pago ya ha sido confirmado");
            return this.confirmacion=true;
        }
        Scanner lector=new Scanner(System.in);
        System.out.println("Confirmando pago");
        if(this.numeroDeTarjetaDeCredito!=null&&this.NIP!=null&&this.getAlias()!=null){
            System.out.println("El numero de su tarjeta de credito es: "+this.numeroDeTarjetaDeCredito);
            System.out.println("Favor de confirmar el NIP de su tarjeta de crédito");
            String confirmacionDeNIP=lector.next();
            if(this.NIP.equals(confirmacionDeNIP)){
                System.out.println("Pago realizado exitosamente");
                return this.confirmacion=true;
            }else{
                System.out.println("Error, la contraseña no coincide, si quiere confirmar la compra, favor de ingresar de nuevo");
                return this.confirmacion=false;
            }
        }
        System.out.println("Faltan datos para realizar la compra");
        return this.confirmacion=false;
    } 
}

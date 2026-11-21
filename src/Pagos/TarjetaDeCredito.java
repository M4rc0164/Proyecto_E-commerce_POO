package Pagos;

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
        System.out.println("Confirmando pago");
        if(this.numeroDeTarjetaDeCredito!=null&&this.NIP!=null&&this.getAlias()!=null){
            System.out.println("Pago confirmado");
        }else{
            System.out.println("Faltan datos para realizar la compra");
        }
    }
    
    
}

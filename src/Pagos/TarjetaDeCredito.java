package Pagos;
import java.util.Objects;
import utilidades.LeerDatos;
/**
 *
 * @author Velazquez De Jesus Dante Yabe
 */
public class TarjetaDeCredito extends MedioDePago{
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

    // Implementación que enmascara los últimos 4 dígitos para menús y tickets
    @Override
    public String getDescripcionTicket() {
        if (this.numeroDeTarjetaDeCredito == null || this.numeroDeTarjetaDeCredito.length() <= 4) {
            return "Tarjeta de crédito (****)";
        }
        String ultimos4 = this.numeroDeTarjetaDeCredito.substring(this.numeroDeTarjetaDeCredito.length() - 4);
        return "Tarjeta de crédito (**** **** **** " + ultimos4 + ")";
    }

    // Se eliminó el atributo booleano "confirmacion" permanente para que valide el NIP en cada compra,
    // se usa LeerDatos.pedirTexto() para evitar múltiples Scanner y se corrigió el mensaje de error (decía contraseña en vez de NIP)
    @Override
    public boolean confirmarPago() {
        System.out.println("Confirmando pago");
        if(this.numeroDeTarjetaDeCredito!=null&&this.NIP!=null&&this.getAlias()!=null){
            System.out.println("El numero de su tarjeta de credito es: "+this.numeroDeTarjetaDeCredito);
            String confirmacionDeNIP=LeerDatos.pedirTexto("Favor de confirmar el NIP de su tarjeta de crédito: ");
            if(this.NIP.equals(confirmacionDeNIP)){
                System.out.println("Pago realizado exitosamente");
                return true;
            }else{
                System.out.println("Error, el NIP no coincide, si quiere confirmar la compra, favor de intentar de nuevo");
                return false;
            }
        }
        System.out.println("Faltan datos para realizar la compra");
        return false;
    }

    // Se agregaron hashCode y equals por número de tarjeta para que Cliente.agregarMedioPago() detecte tarjetas duplicadas
    @Override
    public int hashCode() {
        return Objects.hash(this.numeroDeTarjetaDeCredito);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final TarjetaDeCredito other = (TarjetaDeCredito) obj;
        return Objects.equals(this.numeroDeTarjetaDeCredito, other.numeroDeTarjetaDeCredito);
    }
}

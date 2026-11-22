package Pagos;
import java.util.Objects;
import utilidades.LeerDatos;
/**
 *
 * @author Velazquez De Jesus Dante Yabe
 */
public class Paypal extends MedioDePago{
    private String correoElectronico;
    private String contraseña;

    public Paypal(String correoElectronico, String contraseña, String alias) {
        super(alias);
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Implementación para mostrar la cuenta PayPal en el menú y en el ticket
    @Override
    public String getDescripcionTicket() {
        return "PayPal (" + this.correoElectronico + ")";
    }
 
    @Override
    public boolean confirmarPago() {
        System.out.println("Confirmando pago");
        if(this.contraseña!=null&&this.correoElectronico!=null&&this.getAlias()!=null){
            System.out.println("Favor de confirmar la contraseña de su correo electronico");
            System.out.println("Su correo es: "+this.correoElectronico);
            String confirmacionDeContraseña=LeerDatos.pedirTexto("Ingresa la contraseña: ");
            if(this.contraseña.equals(confirmacionDeContraseña)){
                System.out.println("Pago realizado exitosamente");
                return true;
            }else{
                System.out.println("Contraseña incorrecta, favor de intentar de ingresar de nuevo si quiere confirmar su compra");
                return false;
            }
        }
        System.out.println("Faltan datos para realizar la compra");
        return false;
    }

    // Se agregaron hashCode y equals por correo para que Cliente.agregarMedioPago() detecte cuentas repetidas
    @Override
    public int hashCode() {
        return Objects.hash(this.correoElectronico);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Paypal other = (Paypal) obj;
        return Objects.equals(this.correoElectronico, other.correoElectronico);
    }
    
}

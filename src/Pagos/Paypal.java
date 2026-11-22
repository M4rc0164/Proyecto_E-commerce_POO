package Pagos;
import java.util.Scanner;
/**
 *
 * @author Velazquez De Jesus Dante Yabe
 */
public class Paypal extends MedioDePago{
    private String correoElectronico;
    private String contraseña;
    private boolean confirmacion=false;

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

    @Override
    public boolean confirmarPago() {
        if(this.confirmacion==true){
            System.out.println("El pago ya ha sido confirmado");
            return this.confirmacion=true;
        }
        Scanner lector=new Scanner(System.in);
        System.out.println("Confirmando pago");
        if(this.contraseña!=null&&this.correoElectronico!=null&&this.getAlias()!=null){
            System.out.println("Favor de confirmar la constraseña de su correo electronico");
            System.out.println("Su correo es: "+this.correoElectronico);
            System.out.println("Ingresa la contraseña: ");
            String confirmacionDeContraseña=lector.next();
            if(this.contraseña.equals(confirmacionDeContraseña)){
                System.out.println("Pago realizado exitosamente");
                return this.confirmacion=true;
            }else{
                System.out.println("Contraseña incorrecta, favor de intentar de ingresar de nuevo si quiere confirmar su compra");
                return this.confirmacion=false;
            }
        }
        System.out.println("Faltan datos para realizar la compra");
        return this.confirmacion=false;
    }
    
}

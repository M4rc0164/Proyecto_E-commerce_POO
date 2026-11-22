package usuarios;
import java.util.Scanner;
/**
 *
 * @author Velazquez De Jesus Dante Yabe
 */
public class Domicilio {
    private String calle;
    private String colonia;
    private String numeroDeTelefono;

    public Domicilio(String calle, String colonia, String numeroDeTelefono) {
        this.calle = calle;
        this.colonia = colonia;
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }
    
    public void cambiarTelefono(){
        Scanner lector=new Scanner(System.in);
        System.out.println("¿Desea cambiar de numero de telefono? S/N");
        String confirmacion=lector.next();
        String confirmacionEnMinuscula=confirmacion.toLowerCase();
        if(confirmacion.contains("s")){
            System.out.println("¿A que numero de telefono quiere cambiar?");
            String nuevoNumeroDeTelefono=lector.next();
            this.numeroDeTelefono=nuevoNumeroDeTelefono;
        }
    }
}

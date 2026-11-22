package usuarios;
import java.util.Objects;
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
        if(confirmacionEnMinuscula.contains("s")){
            System.out.println("¿A que numero de telefono quiere cambiar?");
            String nuevoNumeroDeTelefono=lector.next();
            this.numeroDeTelefono=nuevoNumeroDeTelefono;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.calle);
        hash = 79 * hash + Objects.hashCode(this.colonia);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Domicilio other = (Domicilio) obj;
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        return Objects.equals(this.colonia, other.colonia);
    }
    
    public void mostrarDomicilio(){
        System.out.println("El domicilio del usuario es: ");
        System.out.println("Calle: "+this.calle);
        System.out.println("Colonia: "+this.colonia);
        System.out.println("Numero de telefono: "+this.numeroDeTelefono);
    }
}

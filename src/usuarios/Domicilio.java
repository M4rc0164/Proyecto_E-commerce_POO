package usuarios;
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
        System.out.println("¿Desea cambiar de numero de telefono?");
    }
}

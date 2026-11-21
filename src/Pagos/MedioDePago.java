package Pagos;
/**
 *
 * @author Velazquez De Jesus Dante Yabe
 */
public abstract class MedioDePago {
    private String alias;

    public MedioDePago(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    public abstract void confirmarPago();
}

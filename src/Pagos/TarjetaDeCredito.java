/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pagos;

/**
 *
 * @author dell2
 */
public class TarjetaDeCredito {
    private String numeroDeTarjetaDeCredito;
    private String NIP;

    public TarjetaDeCredito(String numeroDeTarjetaDeCredito, String NIP) {
        this.numeroDeTarjetaDeCredito = numeroDeTarjetaDeCredito;
        this.NIP = NIP;
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
    
    
}

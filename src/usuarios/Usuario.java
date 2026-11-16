/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package usuarios;

/**
 *
 * @author EdelM
 */
public abstract class Usuario {
    private String user;
    private String nombre;
    private String password;

    public Usuario(String user, String nombre, String password) {
        this.user = user;
        this.nombre = nombre;
        this.password = password;
    }

    public String getUser() {
        return user;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean validarPassword(String password) {
        return this.password.equals(password);
    }
}

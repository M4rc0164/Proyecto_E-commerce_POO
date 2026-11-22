package datos;

import java.util.Objects;

/**
 * @author marco
 */
public class Producto {

    private String nombre;
    private float precio;
    private String id;
    private String categoria;

    // Constructor privado que recibe el Builder
    private Producto(Builder builder) {
        this.nombre = builder.nombre;
        this.precio = builder.precio;
        this.id = builder.id;
        this.categoria = builder.categoria;
    }

    // Clase estática Builder interna
    public static class Builder {

        private String nombre;
        private float precio;
        private String id;
        private String categoria;

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setPrecio(float precio) {
            this.precio = precio;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setCategoria(String categoria) {
            this.categoria = categoria;
            return this;
        }

        // Método que genera la instancia de la clase original
        public Producto build() {
            if (this.precio <= 0) {
                throw new IllegalStateException("El precio debe ser mayor a cero");
            }
            return new Producto(this);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public String getId() {
        return id;
    }

    public String getCategoria() {
        return categoria;
    }

    // Se agregó validación > 0 para evitar modificar el precio a valores inválidos fuera del Builder
    public void setPrecio(float precio) {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero");
        }
        this.precio = precio;
    }

    // Se implementaron hashCode y equals por ID para usar Producto como llave en el Map del Carrito y detectar duplicados
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        return Objects.equals(this.id, other.id);
    }
        
}

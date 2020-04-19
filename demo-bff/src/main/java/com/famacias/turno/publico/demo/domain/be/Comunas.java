package com.famacias.turno.publico.demo.domain.be;

import java.io.Serializable;
import java.util.Objects;

public class Comunas implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String nombre;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Comunas other = (Comunas) obj;
        return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre);
    }

    
    
}

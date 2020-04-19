/**
 * demo-bff
 * 2020
 */
package com.famacias.turno.publico.demofe.domain;

import java.io.Serializable;

/**
 * @author u4710
 *
 */
public class FarmaciaResponse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String nombreLocal;
    String direccion;
    String fono;
    String latitud;
    String longitud;
    public String getNombreLocal() {
        return nombreLocal;
    }
    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getFono() {
        return fono;
    }
    public void setFono(String fono) {
        this.fono = fono;
    }
    public String getLatitud() {
        return latitud;
    }
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }
    public String getLongitud() {
        return longitud;
    }
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FarmaciaResponse [nombreLocal=");
        builder.append(nombreLocal);
        builder.append(", direccion=");
        builder.append(direccion);
        builder.append(", fono=");
        builder.append(fono);
        builder.append(", latitud=");
        builder.append(latitud);
        builder.append(", longitud=");
        builder.append(longitud);
        builder.append("]");
        return builder.toString();
    }

}

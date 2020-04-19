/**
 * demo-bff
 * 2020
 */
package com.famacias.turno.publico.demo.domain;

import java.io.Serializable;

/**
 * @author u4710
 *
 */
public class SearchRequest implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    String comuna;
    String nombreLocal;
    public String getComuna() {
        return comuna;
    }
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
    public String getNombreLocal() {
        return nombreLocal;
    }
    public void setNombreLocal(String nombreLocal) {
        this.nombreLocal = nombreLocal;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SearchRequest [comuna=");
        builder.append(comuna);
        builder.append(", nombreLocal=");
        builder.append(nombreLocal);
        builder.append("]");
        return builder.toString();
    }

    
}

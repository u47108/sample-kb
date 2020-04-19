/**
 * demo-bff
 * 2020
 */
package com.famacias.turno.publico.demofe.domain;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * @author u4710
 *
 */
public class SearchRequest implements Serializable{
    /**
     * 
     */


    private static final long serialVersionUID = 1L;
    @NotNull
    @Size(min=3, max=30)
    String comuna;
    @NotNull
    @Min(4)
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

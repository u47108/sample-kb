package com.famacias.turno.publico.demo.domain.be;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Farmacia implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String fecha;
    @JsonProperty("local_id")
    private String localId;
    @JsonProperty("local_nombre")
    private String localNombre;
    @JsonProperty("comuna_nombre")
    private String comunaNombre;
    @JsonProperty(value="localidad_nombre")
    private String localidadNombre;
    @JsonProperty(value="local_direccion")
    private String localDireccion;
    @JsonProperty(value="funcionamiento_hora_apertura")
    private String funcionamientoHoraAppertura;
    @JsonProperty(value="funcionamiento_hora_cierre")
    private String funcionamientoHoraCierre;
    @JsonProperty(value="local_telefono")
    private String localTelefono;
    @JsonProperty(value="local_lat")
    private String localLat;
    @JsonProperty(value="local_lng")
    private String localLng;
    @JsonProperty(value="funcionamiento_dia")
    private String funcionamientoDia;
    @JsonProperty(value="fk_region")
    private String region;
    @JsonProperty(value="fk_comuna")
    private String comuna;
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getLocalId() {
        return localId;
    }
    public void setLocalId(String localId) {
        this.localId = localId;
    }
    public String getLocalNombre() {
        return localNombre;
    }
    public void setLocalNombre(String localNombre) {
        this.localNombre = localNombre;
    }
    public String getComunaNombre() {
        return comunaNombre;
    }
    public void setComunaNombre(String comunaNombre) {
        this.comunaNombre = comunaNombre;
    }
    public String getLocalidadNombre() {
        return localidadNombre;
    }
    public void setLocalidadNombre(String localidadNombre) {
        this.localidadNombre = localidadNombre;
    }
    public String getLocalDireccion() {
        return localDireccion;
    }
    public void setLocalDireccion(String localDireccion) {
        this.localDireccion = localDireccion;
    }
    public String getFuncionamientoHoraAppertura() {
        return funcionamientoHoraAppertura;
    }
    public void setFuncionamientoHoraAppertura(String funcionamientoHoraAppertura) {
        this.funcionamientoHoraAppertura = funcionamientoHoraAppertura;
    }
    public String getFuncionamientoHoraCierre() {
        return funcionamientoHoraCierre;
    }
    public void setFuncionamientoHoraCierre(String funcionamientoHoraCierre) {
        this.funcionamientoHoraCierre = funcionamientoHoraCierre;
    }
    public String getLocalTelefono() {
        return localTelefono;
    }
    public void setLocalTelefono(String localTelefono) {
        this.localTelefono = localTelefono;
    }
    public String getLocalLat() {
        return localLat;
    }
    public void setLocalLat(String localLat) {
        this.localLat = localLat;
    }
    public String getLocalLng() {
        return localLng;
    }
    public void setLocalLng(String localLng) {
        this.localLng = localLng;
    }
    public String getFuncionamientoDia() {
        return funcionamientoDia;
    }
    public void setFuncionamientoDia(String funcionamientoDia) {
        this.funcionamientoDia = funcionamientoDia;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }
    public String getComuna() {
        return comuna;
    }
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
    

}

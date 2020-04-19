package com.famacias.turno.publico.demofe.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ResponseFarmacias {

    protected ServiceStatus serviceStatus;
    @JsonInclude(Include.NON_NULL)
    private List<FarmaciaResponse> farmacias;
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }
    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
    public List<FarmaciaResponse> getFarmacias() {
        return farmacias;
    }
    public void setFarmacias(List<FarmaciaResponse> farmacias) {
        this.farmacias = farmacias;
    }

    
}

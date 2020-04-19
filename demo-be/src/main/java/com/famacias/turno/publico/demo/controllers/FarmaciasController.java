package com.famacias.turno.publico.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.famacias.turno.publico.demo.domain.Comunas;
import com.famacias.turno.publico.demo.domain.Farmacia;
import com.famacias.turno.publico.demo.domain.FarmaciaResponse;
import com.famacias.turno.publico.demo.domain.ResponseFarmacias;
import com.famacias.turno.publico.demo.domain.SearchRequest;
import com.famacias.turno.publico.demo.domain.ServiceStatus;
import com.famacias.turno.publico.demo.service.FarmaciasService;
import com.famacias.turno.publico.demo.service.LogicConsultComunas;

@RestController("farmaciasController")
@RequestMapping("/api/farmacias-be")
public class FarmaciasController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciasController.class);

    private final LogicConsultComunas logicConsultService;
    private final FarmaciasService farmaciasService;

    @Autowired
    public FarmaciasController(LogicConsultComunas logicConsultService,FarmaciasService  farmaciasService) {
        this.logicConsultService = logicConsultService;
        this.farmaciasService=farmaciasService;
    }

    @PostMapping("/farmaciasturno")
    public ResponseFarmacias consult(@Valid @RequestBody SearchRequest searchRQ) {
        ResponseFarmacias response= new ResponseFarmacias();
        LOGGER.info("Init farmaciasturno method from FarmaciasController");
        Map<String, Comunas> comunas = logicConsultService.consultaDatos();
        ServiceStatus serviceStatus= new ServiceStatus();
        List<FarmaciaResponse> farmacias= new ArrayList<>();
        if(comunas.containsKey(searchRQ.getComuna())) {
            List<Farmacia> farmaciasList = farmaciasService.consult(searchRQ.getComuna());
            if (farmaciasList!= null && !farmaciasList.isEmpty()) {
                Optional<Farmacia> ffv = farmaciasList.stream()
                        .filter((Farmacia ff) -> ff.getLocalNombre().trim().equalsIgnoreCase(searchRQ.getNombreLocal())).findAny();
                if (ffv.isPresent()) {
                    FarmaciaResponse rp= new FarmaciaResponse();
                    rp.setDireccion(ffv.get().getLocalDireccion());
                    rp.setFono(ffv.get().getLocalTelefono());
                    rp.setLatitud(ffv.get().getLocalLat());
                    rp.setLongitud(ffv.get().getLocalLng());
                    rp.setNombreLocal(ffv.get().getLocalNombre().trim());
                    farmacias.add(rp);
                }
                if(!farmacias.isEmpty()) {
                    serviceStatus.setCode(0);
                    serviceStatus.setMessage("found");
                    serviceStatus.setNativeMessage("200");
                    response.setServiceStatus(serviceStatus);
                    response.setFarmacias(farmacias);
                }else {
                    response=completNotfound();
                }
  
            }else {
                response=completNotfound();
            }
        }else {
            response=completNotfound();
        }
        return response;
    }
    private ResponseFarmacias completNotfound() {
        ResponseFarmacias result=new ResponseFarmacias();
        ServiceStatus serviceStatus= new ServiceStatus();
        serviceStatus.setCode(-1);
        serviceStatus.setMessage("Not found");
        serviceStatus.setNativeMessage("404");
        result.setServiceStatus(serviceStatus);
        return result;
    }
}

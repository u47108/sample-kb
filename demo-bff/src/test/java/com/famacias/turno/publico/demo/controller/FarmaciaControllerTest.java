package com.famacias.turno.publico.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.domain.be.Comunas;
import com.famacias.turno.publico.demo.domain.be.FarmaciaResponse;
import com.famacias.turno.publico.demo.domain.be.ResponseFarmacias;
import com.famacias.turno.publico.demo.domain.be.SearchRequest;
import com.famacias.turno.publico.demo.domain.be.ServiceStatus;
import com.famacias.turno.publico.demo.service.SearchService;

@RunWith(SpringRunner.class)
public class FarmaciaControllerTest {

    @Mock
    SearchService searchService;
    @InjectMocks
    FarmaciaController farmaciaController;
    ResponseFarmacias response;
    SearchRequest rq;
    ServiceStatus serviceStatus;
    List<FarmaciaResponse> farmacias;
    Map<String, Comunas> comunas;

    @Before
    public void init() {
        farmaciaController = new FarmaciaController(searchService);
        response= new ResponseFarmacias(); 
        serviceStatus= new ServiceStatus();
        serviceStatus.setCode(0);
        serviceStatus.setMessage("OK");
        serviceStatus.setNativeMessage("asd");
        response.setServiceStatus(serviceStatus);
        farmacias= new ArrayList<FarmaciaResponse>();
        FarmaciaResponse e= new FarmaciaResponse();
        e.setDireccion("direccion");
        e.setFono("fono");
        e.setLatitud("latitud");
        e.setLongitud("longitud");
        e.setNombreLocal("nombreLocal");
        farmacias.add(e);
        response.setFarmacias(farmacias);
        rq=new SearchRequest();
        
    }

    @Test
    public void testSearch() {
        Mockito.when(searchService.search(Mockito.any())).thenReturn(response);
        ResponseFarmacias rs = farmaciaController.search(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }

}

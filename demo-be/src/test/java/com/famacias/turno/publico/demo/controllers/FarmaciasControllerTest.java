package com.famacias.turno.publico.demo.controllers;

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

import com.famacias.turno.publico.demo.domain.Comunas;
import com.famacias.turno.publico.demo.domain.Farmacia;
import com.famacias.turno.publico.demo.domain.FarmaciaResponse;
import com.famacias.turno.publico.demo.domain.ResponseFarmacias;
import com.famacias.turno.publico.demo.domain.SearchRequest;
import com.famacias.turno.publico.demo.domain.ServiceStatus;
import com.famacias.turno.publico.demo.service.FarmaciasService;
import com.famacias.turno.publico.demo.service.LogicConsultComunas;
import com.famacias.turno.publico.demo.utils.ConstantsTest;

@RunWith(SpringRunner.class)
public class FarmaciasControllerTest {
    @Mock
    LogicConsultComunas logicConsultService;
    @Mock
    FarmaciasService farmaciasService;
    @InjectMocks
    FarmaciasController farmaciaController;
    ResponseFarmacias response;
    SearchRequest rq;
    ServiceStatus serviceStatus;
    List<FarmaciaResponse> farmacias;
    List<Farmacia> respo;
    Map<String, Comunas> comunasMap;
    Farmacia element;
    Comunas c;

    @Before
    public void init() {
        farmaciaController = new FarmaciasController(logicConsultService, farmaciasService);
        response = new ResponseFarmacias();
        serviceStatus = new ServiceStatus();
        serviceStatus.setCode(0);
        serviceStatus.setMessage("OK");
        serviceStatus.setNativeMessage("asd");
        response.setServiceStatus(serviceStatus);
        farmacias = new ArrayList<FarmaciaResponse>();
        FarmaciaResponse e = new FarmaciaResponse();
        e.setDireccion("direccion");
        e.setFono("fono");
        e.setLatitud("latitud");
        e.setLongitud("longitud");
        e.setNombreLocal("nombreLocal");
        farmacias.add(e);
        response.setFarmacias(farmacias);
        rq = new SearchRequest();
        rq.setComuna("BUIN");
        rq.setNombreLocal("DR SIMI");
        comunasMap = new HashMap<>();
        c = new Comunas();
        c.setId("1");
        c.setNombre("BUIN");
        comunasMap.put("BUIN", c);
        respo = new ArrayList<>();
        element = new Farmacia();
        element.setComuna("BUIN");
        element.setComunaNombre("BUIN");
        element.setFecha("20200418");
        element.setFuncionamientoDia("funcionamientoDia");
        element.setFuncionamientoHoraAppertura("funcionamientoHoraAppertura");
        element.setFuncionamientoHoraCierre("funcionamientoHoraCierre");
        element.setLocalDireccion("localDireccion");
        element.setLocalId("localId");
        element.setLocalidadNombre("localidadNombre");
        element.setLocalLat("localLat");
        element.setLocalLng("localLng");
        element.setLocalNombre("DR SIMI");
        element.setLocalTelefono("44453521122");
        element.setRegion("7");
        respo.add(element);
    }

    @Test
    public void testSearchOk() {
        Mockito.when(logicConsultService.consultaDatos()).thenReturn(comunasMap);
        Mockito.when(farmaciasService.consult(Mockito.anyString())).thenReturn(respo);
        ResponseFarmacias rs = farmaciaController.consult(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }

    @Test
    public void testSearchNOkcompletNotfound() {
        comunasMap.clear();
        comunasMap.put("PROVIDENCIA", c);
        Mockito.when(logicConsultService.consultaDatos()).thenReturn(comunasMap);
        Mockito.when(farmaciasService.consult(Mockito.anyString())).thenReturn(respo);
        ResponseFarmacias rs = farmaciaController.consult(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }

    @Test
    public void testSearchNoExisteComuna() {
        c.setId("2");
        c.setNombre("PROVIDENCIA");
        comunasMap.put(c.getNombre(), c);
        Mockito.when(logicConsultService.consultaDatos()).thenReturn(comunasMap);
        Mockito.when(farmaciasService.consult(Mockito.anyString())).thenReturn(respo);
        ResponseFarmacias rs = farmaciaController.consult(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }

    @Test
    public void testSearchNoExisteFarmacia() {
        Mockito.when(logicConsultService.consultaDatos()).thenReturn(comunasMap);
        respo = null;
        Mockito.when(farmaciasService.consult(Mockito.anyString())).thenReturn(respo);
        ResponseFarmacias rs = farmaciaController.consult(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }

    @Test
    public void testSearchNoExisteFarmaciaAtendiendo() {
        Mockito.when(logicConsultService.consultaDatos()).thenReturn(comunasMap);
        respo.clear();
        element.setLocalNombre("CRUZ VERDE");
        respo.add(element);
        Mockito.when(farmaciasService.consult(Mockito.anyString())).thenReturn(respo);
        ResponseFarmacias rs = farmaciaController.consult(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }
}

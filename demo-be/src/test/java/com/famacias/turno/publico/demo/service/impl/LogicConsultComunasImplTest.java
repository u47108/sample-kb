package com.famacias.turno.publico.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.famacias.turno.publico.demo.domain.Comunas;
import com.famacias.turno.publico.demo.domain.FarmaciaResponse;
import com.famacias.turno.publico.demo.domain.ResponseFarmacias;
import com.famacias.turno.publico.demo.domain.SearchRequest;
import com.famacias.turno.publico.demo.domain.ServiceStatus;
import com.famacias.turno.publico.demo.utils.ConstantsTest;

@TestPropertySource("classpath:/application-${spring.profiles.active:local}.properties")
@SpringBootTest
@ActiveProfiles("local")
@RunWith(SpringRunner.class)
public class LogicConsultComunasImplTest {


    @Mock
    private RestTemplate restTemplate;
    @Mock
    LogicConsultComunasImpl logicConsultService;
    ResponseFarmacias response;
    ServiceStatus serviceStatus;
    List<FarmaciaResponse> farmacias;
    SearchRequest rq;
    Map<String, Comunas> map;
    String rep;
    ResponseEntity<String> entityResponse;
    Comunas elem;
    @Mock
    private RestClientException httpStatusCodeException;

    @Mock
    private ResourceAccessException resourceAccessException;
    HttpEntity<MultiValueMap<String, String>> requestEntity;
    HttpHeaders httpHeaders = new HttpHeaders();
    @Before
    public void init() {
        logicConsultService = new LogicConsultComunasImpl(restTemplate, "localhost", "param", "7");
        ReflectionTestUtils.setField(logicConsultService, "restTemplate", restTemplate);
        ReflectionTestUtils.setField(logicConsultService, "endpoint", "localhost");
        ReflectionTestUtils.setField(logicConsultService, "param", "param");
        ReflectionTestUtils.setField(logicConsultService, "paramValue", "7");
        map = new HashMap<>();
        elem = new Comunas();
        elem.setId("1");
        elem.setNombre("BUIN");
        map.put("BUIN", elem);
        rep = "<option value='82'>ALHUE</option>\n" + "<option value='83'>BUIN</option>\n"
                + "<option value='84'>CALERA DE TANGO</option>";
        entityResponse = new ResponseEntity<>(rep, HttpStatus.OK);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("param", "7");
        requestEntity=new HttpEntity<>(map, this.httpHeaders);
    }

    @Test
    public void testConsultaDatosOk() {
        Mockito.when(restTemplate.exchange(Mockito.any(), Mockito.eq(HttpMethod.POST), Mockito.any(),
                Mockito.eq(String.class))).thenReturn(entityResponse);
        Map<String, Comunas> rs = logicConsultService.consultaDatos();
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }

    @Test
    public void testConsultaDatosNOkNull() {
        Mockito.when(restTemplate.exchange(Mockito.any(), Mockito.eq(HttpMethod.POST), Mockito.any(),
                Mockito.eq(String.class))).thenReturn(null);
        Map<String, Comunas> rs = logicConsultService.consultaDatos();
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }
    
    @Test
    public void testConsultaDatosException() {
        Mockito.when(restTemplate.exchange(Mockito.any(), Mockito.eq(HttpMethod.POST), Mockito.any(),
                Mockito.eq(String.class))).thenThrow(httpStatusCodeException);
        Map<String, Comunas> rs = logicConsultService.consultaDatos();
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }
}

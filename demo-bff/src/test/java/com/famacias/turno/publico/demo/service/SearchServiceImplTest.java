package com.famacias.turno.publico.demo.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.famacias.turno.publico.demo.controller.ConstantsTest;
import com.famacias.turno.publico.demo.domain.be.FarmaciaResponse;
import com.famacias.turno.publico.demo.domain.be.ResponseFarmacias;
import com.famacias.turno.publico.demo.domain.be.SearchRequest;
import com.famacias.turno.publico.demo.domain.be.ServiceStatus;

@RunWith(SpringRunner.class)
public class SearchServiceImplTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    SearchServiceImpl service;
    ResponseFarmacias response;
    ServiceStatus serviceStatus;
    List<FarmaciaResponse> farmacias;
    SearchRequest rq;

    @Mock
    private HttpStatusCodeException httpStatusCodeException;

    @Mock
    private ResourceAccessException resourceAccessException;

    @Before
    public void init() {
        service = new SearchServiceImpl(restTemplate, "localhost");
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
        rq.setComuna("comuna");
        rq.setNombreLocal("nombreLocal");
        ReflectionTestUtils.setField(service, "restTemplate", restTemplate);
    }

    @Test
    public void testSearch(){

        ResponseEntity<ResponseFarmacias> entityResponse = new ResponseEntity<>(response, HttpStatus.OK);
        when(restTemplate.postForObject(Mockito.any(), Mockito.eq(HttpMethod.POST), Mockito.any(),
                Mockito.eq(ResponseFarmacias.class))).thenReturn(entityResponse);
        Mockito.when(service.search(rq)).thenReturn(response);
        ResponseFarmacias rs = service.search(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);
    }

    @Test
    public void testExceptionSearch(){
        Mockito.when(
                restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.eq(ResponseFarmacias.class)))
                .thenThrow(new ResourceAccessException("<ResourceAccessException>"));
        ResponseFarmacias rs = service.search(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rs);

    }
    @Test
    public void testExceptionHttpStatusSearch() {

        Mockito.when(
                restTemplate.postForObject(Mockito.anyString(), Mockito.any(), Mockito.eq(ResponseFarmacias.class)))
                .thenThrow(httpStatusCodeException);
        ResponseFarmacias rsp = service.search(rq);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rsp);

    }
}

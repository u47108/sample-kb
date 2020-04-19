package com.famacias.turno.publico.demo.service.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.famacias.turno.publico.demo.domain.Farmacia;
import com.famacias.turno.publico.demo.utils.ConstantsTest;
import com.famacias.turno.publico.demobe.SpringTestConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTestConfig.class)
public class FarmaciaServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Mock
    FarmaciaServiceImpl service;
    Farmacia pojo;
    List<Farmacia> rs = new ArrayList<>();
  
    @Mock
    private HttpStatusCodeException httpStatusCodeException;

    @Mock
    private ResourceAccessException resourceAccessException;
    ResponseEntity<Farmacia[]> entityResponse;
    @Mock
    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();
 

    @Before
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        service = new FarmaciaServiceImpl(restTemplate,"localhost");
        //ReflectionTestUtils.setField(service, "restTemplate", restTemplate);
        pojo = new Farmacia();
        pojo.setComuna("comuna");
        pojo.setComunaNombre("comunaNombre");
        pojo.setFecha("fecha");
        pojo.setFuncionamientoDia("funcionamientoDia");
        pojo.setFuncionamientoHoraAppertura("funcionamientoHoraAppertura");
        pojo.setFuncionamientoHoraCierre("funcionamientoHoraCierre");
        pojo.setLocalDireccion("localDireccion");
        pojo.setLocalId("localId");
        pojo.setLocalidadNombre("localidadNombre");
        pojo.setLocalLat("localLat");
        pojo.setLocalLng("localLng");
        pojo.setLocalTelefono("localTelefono");
        pojo.setRegion("region");
        pojo.setLocalNombre("localNombre");
        rs.add(pojo);
        Farmacia[] sr={pojo};
        entityResponse = new ResponseEntity<Farmacia[]>(sr, HttpStatus.OK);
    }

    @Test
    public void testConsultOk() throws JsonProcessingException, URISyntaxException {
        mockServer.expect(ExpectedCount.never(), 
                requestTo(new URI("http://localhost:8080/employee/E001")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                .contentType(new MediaType("text", "html", StandardCharsets.UTF_8))
                .body(mapper.writeValueAsString(entityResponse.getBody()))
              );                                                              
        List<Farmacia> respuesta = service.consult("comuna");
        mockServer.verify();
        List<Farmacia>  list=new ArrayList<Farmacia>();
        Assert.assertEquals( respuesta,list );
  
    }

    @Test
    public void testFilterNombreComuna() {
        Farmacia[] sr={pojo};
        List<Farmacia> respuestaFilter = service.filterNombreComuna("comunaNombre", sr);
        Assert.assertNotNull(respuestaFilter);
    }
}

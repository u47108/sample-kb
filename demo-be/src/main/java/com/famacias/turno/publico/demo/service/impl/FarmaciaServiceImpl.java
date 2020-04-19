package com.famacias.turno.publico.demo.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.famacias.turno.publico.demo.domain.Farmacia;
import com.famacias.turno.publico.demo.service.FarmaciasService;

@Service
@PropertySource("classpath:/application-${spring.profiles.active:local}.properties")
public class FarmaciaServiceImpl implements FarmaciasService {

    private RestTemplate restTemplate= new RestTemplate();
    private  String endpoint;
    private  HttpHeaders httpHeaders = new HttpHeaders();
    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciaServiceImpl.class);
    @Autowired
    public FarmaciaServiceImpl(RestTemplate restTemplate,@Value("${ws.farmacias.endpoint}") String endpoint) {
        super();

        if (endpoint != null) {
            if (endpoint.startsWith("https")) {
                this.endpoint = endpoint;
            } else {
                this.endpoint = String.format("https://%s", endpoint);
            }
        } else {
            throw new BeanInitializationException("ws.farmacias.endpoint endpoint cant be a null");
        }
     
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
                Arrays.asList(MediaType.TEXT_HTML, new MediaType("text", "html", StandardCharsets.UTF_8)));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        this.restTemplate=restTemplate;
        LOGGER.debug("ws farmacias endpoint: {}", this.endpoint);
        this.httpHeaders.setContentType(new MediaType("text", "html", StandardCharsets.UTF_8));

    }

    /**
     * filtrar las farmacias por comuna
     */
    @Override
    public List<Farmacia> consult(String comuna) {
        List<Farmacia> respuesta = new ArrayList<>();
        String urlRequest = endpoint;
        Farmacia[] arrayComunas = null;
        HttpEntity<?> requestEntity = new HttpEntity<>(null, this.httpHeaders);
        ResponseEntity<Farmacia[]> entityResponse = null;
        try {
            LOGGER.info("request when consume comunas service {} ", comuna);
            entityResponse = restTemplate.exchange(urlRequest, HttpMethod.GET, requestEntity, Farmacia[].class);
        } catch (RestClientException e) {
            LOGGER.error("Error when consume comunas service", e);
        }
        if (entityResponse != null && entityResponse.getBody()!=null) {
            arrayComunas = entityResponse.getBody();
        }else {
            return respuesta;
        }
        return filterNombreComuna(comuna,arrayComunas);
        
    }
    
    /**
     * Filtra por nombre de comuna
     * @param comuna
     * @param arrayComunas
     * @return
     */
    public List<Farmacia> filterNombreComuna(String comuna,Farmacia[] arrayComunas){
        List<Farmacia> respuesta = new ArrayList<>();
        List<Farmacia> lista = Arrays.stream(arrayComunas).collect(Collectors.toList());
        if (lista != null && !lista.isEmpty()) {
            Optional<Farmacia> ffv = lista.stream()
                    .filter((Farmacia ff) -> ff.getComunaNombre().equalsIgnoreCase(comuna)).findFirst();
            if (ffv.isPresent()) {
                respuesta.add(ffv.get());
            }
        }
        return respuesta;
    } 

}

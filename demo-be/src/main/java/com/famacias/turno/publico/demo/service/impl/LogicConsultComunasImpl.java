package com.famacias.turno.publico.demo.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.famacias.turno.publico.demo.domain.Comunas;
import com.famacias.turno.publico.demo.service.LogicConsultComunas;
import com.famacias.turno.publico.demo.utils.MapperUtil;

@Service
@PropertySource("classpath:/application-${spring.profiles.active:local}.properties")
public class LogicConsultComunasImpl implements LogicConsultComunas {

    private RestTemplate restTemplate;
    private String endpoint;
    private String param;
    private String paramValue;
    private HttpHeaders httpHeaders = new HttpHeaders();
    private static final Logger LOGGER = LoggerFactory.getLogger(LogicConsultComunasImpl.class);

    @Autowired
    public LogicConsultComunasImpl(RestTemplate restTemplate, @Value("${ws.comunas.endpoint}") String endpoint,
            @Value("${ws.comunas.parameter.value}") String paramValue,
            @Value("${ws.comunas.parameter.name}") String param) {
        this.restTemplate = restTemplate;
        this.param = param;
        this.paramValue = paramValue;
        if (endpoint != null) {
            if (endpoint.startsWith("https")) {
                this.endpoint = endpoint;
            } else {
                this.endpoint = String.format("https://%s", endpoint);
            }
        } else {
            throw new BeanInitializationException("ws.comunas.endpoint endpoint cant be a null");
        }

        LOGGER.debug("ws comunas endpoint: {}", this.endpoint);
        this.httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        this.httpHeaders.add(this.param, this.paramValue);
        this.httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Override
    public Map<String, Comunas> consultaDatos() {

        Map<String, Comunas> comunas = new HashMap<>();
        String urlRequest = endpoint;
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(this.param, this.paramValue);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, this.httpHeaders);
        ResponseEntity<String> entityResponse = null;
        try {
            LOGGER.info("Body when consume comunas service {} ", requestEntity.getBody());
            entityResponse = restTemplate.exchange(urlRequest, HttpMethod.POST, requestEntity, String.class);
        } catch (RestClientException e) {
            LOGGER.error("Error when consume comunas service", e);
        }
        if (entityResponse != null) {
            String rsBody = StringEscapeUtils.escapeJava(entityResponse.getBody());
            LOGGER.debug("Response: {}", rsBody);
            comunas = MapperUtil.parseRsToComunas(rsBody);
        }
        return comunas;
    }

}

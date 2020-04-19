package com.famacias.turno.publico.demo.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.famacias.turno.publico.demo.domain.be.ResponseFarmacias;
import com.famacias.turno.publico.demo.domain.be.SearchRequest;
import com.famacias.turno.publico.demo.domain.be.ServiceStatus;


@Service
@PropertySource("classpath:/application-${spring.profiles.active:local}.properties")
public class SearchServiceImpl implements SearchService {

    private String endpoint;
    private RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate, @Value("${be.endpoint}") String endpoint) {
        LOGGER.debug("Endpoint: {}", endpoint);
        this.restTemplate = restTemplate;
        this.endpoint = endpoint;
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Override
    public ResponseFarmacias search(SearchRequest request) {
        LOGGER.info("Init authorize method for call the search");
        ResponseFarmacias response = null;
        try {
            HttpEntity<?> entity = new HttpEntity<>(request, SearchServiceImpl.getHttpHeaders());
            LOGGER.debug("Endpoint: {}", endpoint);
            response = restTemplate.postForObject(this.endpoint, entity, ResponseFarmacias.class);
            LOGGER.info("<- BFF Response: {}", response);
        } catch (HttpStatusCodeException | ResourceAccessException exception) {
            LOGGER.error("Consuming REST error: {}", exception);
            ServiceStatus status = new ServiceStatus();
            status.setCode(-1);
            status.setMessage("Error when consume bff");
            status.setNativeMessage(exception.getMessage());
            response = new ResponseFarmacias();
            response.setServiceStatus(status);
        }
        return response;
    }

    /**
     * Method that generates the http headers
     * 
     * @return HttpHeaders
     */
    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
}

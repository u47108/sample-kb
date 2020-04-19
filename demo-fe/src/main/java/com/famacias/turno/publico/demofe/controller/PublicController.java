package com.famacias.turno.publico.demofe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.famacias.turno.publico.demofe.domain.FarmaciaResponse;
import com.famacias.turno.publico.demofe.domain.ResponseFarmacias;
import com.famacias.turno.publico.demofe.domain.SearchRequest;

@Controller
public class PublicController {

    @Value("${maps.apikey}")
    private String apimaps;
    @Value("${bff.endpoint}")
    private String endpoint;

    @GetMapping("/viewmaps")
    public String index(Model model) {
        model.addAttribute("mapsKey", apimaps);
        model.addAttribute("searchRq", new SearchRequest());
        return "maps";
    }

    @PostMapping(value = "/viewmaps")
    public String search(@Valid @ModelAttribute SearchRequest searchRq, BindingResult errors, Model model) {
        if (!errors.hasErrors()) {
            model.addAttribute("mapsKey", apimaps);
            FarmaciaResponse farmacia = new FarmaciaResponse();
            farmacia.setLatitud("-33.732");
            farmacia.setLongitud("-70.735941");
            farmacia.setNombreLocal("AHUMADA");
            List<FarmaciaResponse> listGeo = new ArrayList<>();
            final String uri = endpoint;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<?> entity = new HttpEntity<>(searchRq,getHttpHeaders());
            ResponseFarmacias response = restTemplate.postForObject(this.endpoint, entity, ResponseFarmacias.class);
            if(response!=null && !response.getFarmacias().isEmpty()) {
            model.addAttribute("listGeo", response.getFarmacias());
            }
        }
        return ((errors.hasErrors()) ? "home" : "maps");
    }
    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
}
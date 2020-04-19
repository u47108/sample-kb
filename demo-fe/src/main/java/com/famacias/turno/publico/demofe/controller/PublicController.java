package com.famacias.turno.publico.demofe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.famacias.turno.publico.demofe.domain.FarmaciaResponse;
import com.famacias.turno.publico.demofe.domain.SearchRequest;

@Controller
public class PublicController {

    @Value("${maps.apikey}")
    private String apimaps;

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
            model.addAttribute("listGeo", listGeo);
        }
        return ((errors.hasErrors()) ? "home" : "maps");
    }
}
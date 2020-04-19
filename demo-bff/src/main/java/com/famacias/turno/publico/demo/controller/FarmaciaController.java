/**
 * demo
 * 2020
 */
package com.famacias.turno.publico.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.famacias.turno.publico.demo.domain.be.ResponseFarmacias;
import com.famacias.turno.publico.demo.domain.be.SearchRequest;
import com.famacias.turno.publico.demo.service.SearchService;
import com.famacias.turno.publico.demo.utils.JsonTransformer;

import io.swagger.annotations.ApiOperation;

/**
 * @author u4710
 *
 */
@RestController("farmaciaController")
@RequestMapping("/api/farmacia-turno")
public class FarmaciaController {

 
    SearchService searchService;
    private static final Logger LOGGER = LoggerFactory.getLogger(FarmaciaController.class);
    @Autowired
    public FarmaciaController(SearchService searchService) {
        this.searchService = searchService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/buscarlocal")
    @ApiOperation(value = "busca la farmacia de turno")
    public ResponseFarmacias search(@RequestBody(required = false) SearchRequest request) {
        JsonTransformer.requestOrResponseToString(request, LOGGER);
        return searchService.search(request);
    }

}

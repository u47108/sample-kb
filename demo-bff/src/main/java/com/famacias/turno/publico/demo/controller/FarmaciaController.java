/**
 * demo
 * 2020
 */
package com.famacias.turno.publico.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.famacias.turno.publico.demo.domain.be.FarmaciaResponse;
import com.famacias.turno.publico.demo.domain.be.SearchRequest;

import io.swagger.annotations.ApiOperation;

/**
 * @author u4710
 *
 */
@RestController("farmaciaController")
@RequestMapping("/api/farmacia-turno")
public class FarmaciaController {

  @PostMapping("/buscarlocal")
  @ApiOperation(value = "busca la farmacia de turno")
  public FarmaciaResponse search(SearchRequest request) {
    return null;
    
  }
  
}

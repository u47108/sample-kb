/**
 * demo
 * 2020
 */
package com.famacias.turno.publico.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author u4710
 *
 */
@RestController
@RequestMapping("/api/farmacia-turno")
public class HealcheckController {

  /**
   * Endpoint para hacer healthcheck
   */
  @CrossOrigin(origins = "*")
  @GetMapping(value = "/healthcheck")
  public ResponseEntity<String> healthcheck() {
    return new ResponseEntity<>("OK STATUS 200", HttpStatus.OK);
  }
}

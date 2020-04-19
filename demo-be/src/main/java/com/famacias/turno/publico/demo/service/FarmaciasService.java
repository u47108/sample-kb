package com.famacias.turno.publico.demo.service;

import java.util.List;

import com.famacias.turno.publico.demo.domain.Farmacia;

public interface FarmaciasService {
    
    List<Farmacia> consult(String comuna);

}

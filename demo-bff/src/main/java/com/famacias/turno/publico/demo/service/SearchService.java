package com.famacias.turno.publico.demo.service;

import com.famacias.turno.publico.demo.domain.be.ResponseFarmacias;
import com.famacias.turno.publico.demo.domain.be.SearchRequest;

public interface SearchService {
    
    ResponseFarmacias search(SearchRequest request);

}

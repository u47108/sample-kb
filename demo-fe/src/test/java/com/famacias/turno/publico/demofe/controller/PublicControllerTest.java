package com.famacias.turno.publico.demofe.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PublicController.class)
public class PublicControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // Get request with Param
    @Test
    public void search() throws Exception {
        mockMvc.perform(get("/viewmaps").param("name", "Mapas"))
                .andExpect(status().isOk())
                .andExpect(view().name("maps"));
    }

}

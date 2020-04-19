package com.famacias.turno.publico.demo.utils;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.domain.Comunas;

@RunWith(SpringRunner.class)
public class MapperUtilTest {
    private static String rsBody = "<option value='0' selected>Elija Comuna</option>\n"
            + "<option value='82'>ALHUE</option>\n" + "<option value='83'>BUIN</option>\n"
            + "<option value='84'>CALERA DE TANGO</option>";
    
    @Test
    public void testMapperutil(){
        Comunas pojo = new Comunas();
        pojo.setId("1");
        pojo.setNombre("asd");
        Map<String, Comunas> rep = MapperUtil.parseRsToComunas(rsBody);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, rep);
        
    }

}

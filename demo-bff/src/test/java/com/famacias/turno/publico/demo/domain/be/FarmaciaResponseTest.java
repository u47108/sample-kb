package com.famacias.turno.publico.demo.domain.be;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.controller.ConstantsTest;

@RunWith(SpringRunner.class)
public class FarmaciaResponseTest {

    @Test
    public void testFarmaciasDomain() {
        FarmaciaResponse pojo=new FarmaciaResponse();
        pojo.setDireccion("direccion");
        pojo.setFono("fono");
        pojo.setLatitud("latitud");
        pojo.setLongitud("longitud");
        pojo.setNombreLocal("nombreLocal");
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getDireccion());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getFono());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLatitud());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLongitud());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getNombreLocal());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getSerialversionuid());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.toString());
    }
}

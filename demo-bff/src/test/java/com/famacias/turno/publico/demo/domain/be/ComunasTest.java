package com.famacias.turno.publico.demo.domain.be;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.controller.ConstantsTest;

@RunWith(SpringRunner.class)
public class ComunasTest {
    @Test
    public void testComunas() {
        Comunas pojo = new Comunas();
        pojo.setId("1");
        pojo.setNombre("asd");
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getId());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getNombre());
        Comunas one = pojo;
        Comunas two = new Comunas();
        Assert.assertEquals("These should be equal", one, pojo);
        Assert.assertNotEquals(one, two);
        int oneCode = one.hashCode();
        Assert.assertEquals("HashCodes should be equal", oneCode, pojo.hashCode());
        Assert.assertEquals("HashCode should not change", oneCode, one.hashCode());
        Assert.assertEquals("HashCode should not change", oneCode, one.hashCode());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.toString());
    }

}

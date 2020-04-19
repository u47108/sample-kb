package com.famacias.turno.publico.demo.domain.be;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.controller.ConstantsTest;

@RunWith(SpringRunner.class)
public class ResponseFarmaciasTest {

    @Test
    public void testResponseFarmacias() {
        ResponseFarmacias pojo = new ResponseFarmacias();
        List<FarmaciaResponse> farmacias= new ArrayList<>();
        pojo.setFarmacias(farmacias);
        ServiceStatus serviceStatus=new ServiceStatus();
        pojo.setServiceStatus(serviceStatus);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getFarmacias());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getServiceStatus());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.toString());
    }
    
}

package com.famacias.turno.publico.demo.domain.be;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.controller.ConstantsTest;

@RunWith(SpringRunner.class)
public class ServiceStatusTest {

    @Test
    public void testStatus() {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setCode(ConstantsTest.CODE_OK);
        serviceStatus.setMessage(ConstantsTest.DESCRIPTION);
        serviceStatus.setNativeMessage(ConstantsTest.DESCRIPTION);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, serviceStatus);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, serviceStatus.getCode());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, serviceStatus.getMessage());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, serviceStatus.getNativeMessage());
    }
}

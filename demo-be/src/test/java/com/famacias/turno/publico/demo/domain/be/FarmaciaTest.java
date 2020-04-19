package com.famacias.turno.publico.demo.domain.be;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.domain.Farmacia;
import com.famacias.turno.publico.demo.utils.ConstantsTest;

@RunWith(SpringRunner.class)
public class FarmaciaTest {
    @Test
    public void testFarmacia() {
        Farmacia pojo = new Farmacia();
        pojo.setComuna("comuna");
        pojo.setComunaNombre("comunaNombre");
        pojo.setFecha("fecha");
        pojo.setFuncionamientoDia("funcionamientoDia");
        pojo.setFuncionamientoHoraAppertura("funcionamientoHoraAppertura");
        pojo.setFuncionamientoHoraCierre("funcionamientoHoraCierre");
        pojo.setLocalDireccion("localDireccion");
        pojo.setLocalId("localId");
        pojo.setLocalidadNombre("localidadNombre");
        pojo.setLocalLat("localLat");
        pojo.setLocalLng("localLng");
        pojo.setLocalTelefono("localTelefono");
        pojo.setRegion("region");
        pojo.setLocalNombre("localNombre");
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getComuna());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getComunaNombre());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getFecha());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getFuncionamientoDia());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getFuncionamientoHoraAppertura());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getFuncionamientoHoraCierre());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLocalDireccion());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLocalidadNombre());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLocalId());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLocalLat());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLocalLng());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLocalTelefono());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getRegion());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getLocalNombre());
    }
}

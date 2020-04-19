package com.famacias.turno.publico.demo.domain.be;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.domain.SearchRequest;
import com.famacias.turno.publico.demo.utils.ConstantsTest;

@RunWith(SpringRunner.class)
public class SearchRequestTest {
    
    @Test
    public void testSearchRequest() {
        SearchRequest pojo = new SearchRequest();
        pojo.setComuna("comuna");
        pojo.setNombreLocal("nombreLocal");
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo);
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getComuna());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.getNombreLocal());
        SearchRequest one = pojo;
        Assert.assertEquals("These should be equal", one, pojo);
        int oneCode = one.hashCode();
        Assert.assertEquals("HashCodes should be equal", oneCode, pojo.hashCode());
        Assert.assertEquals("HashCode should not change", oneCode, one.hashCode());
        Assert.assertNotNull(ConstantsTest.MUST_BE_A_NOTNULL, pojo.toString());
    }


}

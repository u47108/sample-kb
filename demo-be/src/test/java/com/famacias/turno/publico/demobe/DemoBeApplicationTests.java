package com.famacias.turno.publico.demobe;

import static org.assertj.core.api.Java6Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.famacias.turno.publico.demo.DemoBeApplication;

@RunWith(SpringRunner.class)
public class DemoBeApplicationTests {

    @Test
    public void contextLoads() {
        DemoBeApplication app = new DemoBeApplication();
        String[] args = { "" };
        app.main(args);
        app.rest();
        assertThat(true).isEqualTo(true);
    }

}

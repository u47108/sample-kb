package com.famacias.turno.publico.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	    DemoApplication app = new DemoApplication();
	    String[] args={""};
        app.main(args);
        app.farmaciasApi();
        assertThat(true).isEqualTo(true);
	}

}

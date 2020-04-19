
package com.famacias.turno.publico.demo;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.famacias.turno.publico.demo.filters.CorsFilter;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {
        MultipartAutoConfiguration.class,
        JmxAutoConfiguration.class,
      })
@EnableSwagger2
public class DemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);
    private static final int DEFAULT_TIMEOUT = 25000;
    
    @Value("${be.timeout}")
    private String beTimeout;
    
    public DemoApplication() {
        super();
      }
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	 public Docket farmaciasApi(){
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select().apis(RequestHandlerSelectors.basePackage("com.famacias.turno.publico.demo.controller"))
	        .build();
	  }
	 
	 /**
	   * Initializes the bean RestTemplate
	   * 
	   * @return RestTemplate
	   */
	  @Bean
	  public RestTemplate rest() {
	    try {

	      CloseableHttpClient httpClient = HttpClients.custom().build();
	      HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
	      httpRequestFactory.setHttpClient(httpClient);
	      
	      
	      int timeout = DEFAULT_TIMEOUT;
	      if(beTimeout != null) {
	        timeout = Integer.valueOf(beTimeout);
	      }
	      LOGGER.debug("Timeout: {}", beTimeout);
	      httpRequestFactory.setConnectionRequestTimeout(timeout);
	      httpRequestFactory.setConnectTimeout(timeout);
	      httpRequestFactory.setReadTimeout(timeout);
	      LOGGER.debug("INIT demo-bff");
	      return new RestTemplate(httpRequestFactory);

	    } catch (ExceptionInInitializerError e) {
	      throw new BeanInitializationException("Can't create Rest Template", e);
	    }
	  }
	  
	  @Bean
	  public FilterRegistrationBean<CorsFilter> corsFilter() {
	    FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
	    CorsFilter corsFilter = new CorsFilter();
	    registrationBean.setFilter(corsFilter);
	    registrationBean.addUrlPatterns("/*");
	    return registrationBean;
	  }
}

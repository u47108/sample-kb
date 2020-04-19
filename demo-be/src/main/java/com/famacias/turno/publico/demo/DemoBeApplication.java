package com.famacias.turno.publico.demo;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = { MultipartAutoConfiguration.class, JmxAutoConfiguration.class, })
@ComponentScan("com.famacias.turno.publico.demo")
public class DemoBeApplication {
    /** The Constant DEFAULT_TIMEOUT. */
    private static final int DEFAULT_TIMEOUT = 25000;

    public static void main(String[] args) {
        SpringApplication.run(DemoBeApplication.class, args);
    }

    @Bean
    public RestTemplate rest() {
        try {
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy).build();

            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpRequestFactory.setHttpClient(httpClient);

            String sTimeout = System.getenv("TIMEOUT");
            // Valor por defecto
            int timeout = DEFAULT_TIMEOUT;
            if (sTimeout != null) {
                timeout = Integer.parseInt(sTimeout);
            }
            httpRequestFactory.setConnectionRequestTimeout(timeout);
            httpRequestFactory.setConnectTimeout(timeout);
            httpRequestFactory.setReadTimeout(timeout);

            return new RestTemplate(httpRequestFactory);
        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
            throw new BeanInitializationException("Can't generate Rest Template", e);
        }
    }
}

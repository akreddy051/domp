package com.example.payment_service.config;

import java.net.http.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.TrustManagerFactory;


@Configuration
public class AppConfig {

    @Value("${ssl.trust-store}")
    private Resource trustStore;

    @Value("${ssl.trust-store-password}")
    private String trustStorePassword;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() throws Exception {

        KeyStore ts = KeyStore.getInstance("JKS");
        try (InputStream is = trustStore.getInputStream()) {
            ts.load(is, trustStorePassword.toCharArray());
        }

        TrustManagerFactory tmf =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ts);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        HttpClient httpClient = HttpClient.newBuilder()
                .sslContext(sslContext)
                .build();

        return new RestTemplate(new JdkClientHttpRequestFactory(httpClient));
    }
}
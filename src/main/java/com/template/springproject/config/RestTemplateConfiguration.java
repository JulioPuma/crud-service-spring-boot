package com.template.springproject.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class RestTemplateConfiguration {

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder, LoggingInterceptor loggingInterceptor) {
//    return builder
//        .additionalInterceptors(loggingInterceptor)
//        .additionalMessageConverters(new MappingJackson2HttpMessageConverter())
//        .build();
//    }

//    @Bean
//    public RestTemplate restTemplate(LoggingInterceptor loggingInterceptor) {
//
//        RestTemplate restTemplate =
//                new RestTemplate(
//                        new BufferingClientHttpRequestFactory(
//                                new SimpleClientHttpRequestFactory()));
//
//        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
//        interceptors.add(loggingInterceptor);
//        restTemplate.setInterceptors(interceptors);
//        return restTemplate;
//    }

    @Bean
    public RestTemplate restTemplate(LoggingInterceptor loggingInterceptor) {
        return new RestTemplate();
    }

//    @Bean
//    public RestTemplate getRestTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        CloseableHttpClient httpClient =
//                HttpClients.custom()
//                    .setConnectionManager(
//                            PoolingHttpClientConnectionManagerBuilder
//                                    .create()
//                                    .setSSLSocketFactory(
//                                            SSLConnectionSocketFactoryBuilder
//                                            .create()
//                                            .setSslContext(SSLContextBuilder
//                                                    .create()
//                                                    .loadTrustMaterial(TrustAllStrategy.INSTANCE)
//                                                    .build())
//                                            .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
//                                    .build())
//                    .build())
//                .build();
//        HttpComponentsClientHttpRequestFactory requestFactory =
//                new HttpComponentsClientHttpRequestFactory(httpClient);
//        //requestFactory.setHttpClient(httpClient);// this method is not accepting the CloseableHttpClient object
//        requestFactory.setConnectTimeout(30000);
//        //requestFactory.setReadTimeout(30000); //This method is deprecated in spring boot 3.0
//        requestFactory.setConnectionRequestTimeout(30000);
//        return new RestTemplate(requestFactory);
//    }
}

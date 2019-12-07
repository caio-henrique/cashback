package com.cashback.integration.api.authentication.configuration;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationSpotifyConfiguration implements RequestInterceptor {

    @Value("${authentication.spotify.token}")
    private String token;

//    @Bean
//    public RequestInterceptor requestInterceptorAuthentication() {
//        return requestTemplate -> {
//            requestTemplate.header("Authorization", "Basic " + token);
//        };
//    }

    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header("Authorization", "Basic " + token);
    }
}

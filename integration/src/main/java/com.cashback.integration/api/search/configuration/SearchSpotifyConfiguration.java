package com.cashback.integration.api.search.configuration;

import com.cashback.integration.api.authentication.AuthenticationSpotifyClient;
import com.cashback.integration.api.authentication.AuthenticationSpotifyResponse;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchSpotifyConfiguration implements RequestInterceptor{

    private AuthenticationSpotifyClient authenticationSpotifyClient;

    public SearchSpotifyConfiguration(AuthenticationSpotifyClient authenticationSpotifyClient) {
        this.authenticationSpotifyClient = authenticationSpotifyClient;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {

        AuthenticationSpotifyResponse authenticate = authenticationSpotifyClient.authenticate("");
        requestTemplate.header("Authorization",
                authenticate.getTokenType() + " " + authenticate.getAccessToken());
    }
}

package com.cashback.integration;

import com.cashback.integration.api.authentication.AuthenticationSpotifyClient;
import com.cashback.integration.api.authentication.AuthenticationSpotifyResponse;
import com.cashback.integration.authentication.AuthenticationIntegration;
import com.cashback.integration.authentication.AuthenticationIntegrationResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationIntegrationImpl implements AuthenticationIntegration {

    private AuthenticationSpotifyClient authenticationSpotifyClient;

    public AuthenticationIntegrationImpl(AuthenticationSpotifyClient authenticationSpotifyClient) {
        this.authenticationSpotifyClient = authenticationSpotifyClient;
    }

    @Override
    public AuthenticationIntegrationResponse authenticate() {

        AuthenticationSpotifyResponse authenticate = authenticationSpotifyClient.authenticate("");
        return AuthenticationIntegrationResponse.valueOf(authenticate.getAccessToken());
    }
}

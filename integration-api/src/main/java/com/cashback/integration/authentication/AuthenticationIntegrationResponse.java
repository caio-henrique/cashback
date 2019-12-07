package com.cashback.integration.authentication;

import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.Objects;

public class AuthenticationIntegrationResponse implements Serializable {

    private String accessToken;

    private AuthenticationIntegrationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    @Bean
    public static AuthenticationIntegrationResponse valueOf(String accessToken) {

        AuthenticationIntegrationResponse addressMigrationIntegrationResponse =
                new AuthenticationIntegrationResponse(accessToken);
//        IntegrationObjectValidator.validate(ValidationCodes.ERROR_RESPONSE_INTEGRATION_ADDRESS_MIGRATION,
//                addressMigrationIntegrationResponse);
        return addressMigrationIntegrationResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationIntegrationResponse that = (AuthenticationIntegrationResponse) o;
        return Objects.equals(accessToken, that.accessToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken);
    }

    @Override
    public String toString() {
        return "AuthenticationIntegrationResponse{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}

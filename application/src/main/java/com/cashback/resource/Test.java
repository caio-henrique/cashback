package com.cashback.resource;

import com.cashback.common.enums.Gender;
import com.cashback.integration.authentication.AuthenticationIntegration;
import com.cashback.integration.authentication.AuthenticationIntegrationResponse;
import com.cashback.integration.search.SearchIntegration;
import com.cashback.integration.search.SearchIntegrationRequest;
import com.cashback.integration.search.SearchIntegrationResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teste")
public class Test {

    private AuthenticationIntegration authenticationIntegration;
    private SearchIntegration searchIntegration;

    public Test(AuthenticationIntegration authenticationIntegration, SearchIntegration searchIntegration) {
        this.authenticationIntegration = authenticationIntegration;
        this.searchIntegration = searchIntegration;
    }

    @GetMapping(path = "/1")
    public AuthenticationIntegrationResponse  test() {
        return authenticationIntegration.authenticate();
    }


    @GetMapping(path = "/2")
    public SearchIntegrationResponse test2() {

        return searchIntegration.searchByGenre(SearchIntegrationRequest.valueOf(Gender.POP, 50));
    }
}

package com.cashback.integration.api.authentication;

import com.cashback.integration.api.authentication.configuration.AuthenticationSpotifyConfiguration;
import feign.Body;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "authenticationSpotifyClient", url = "https://accounts.spotify.com/api/token?grant_type=client_credentials",
        configuration = AuthenticationSpotifyConfiguration.class)
public interface AuthenticationSpotifyClient {

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    AuthenticationSpotifyResponse authenticate(@RequestBody String body);
}


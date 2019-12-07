package com.cashback.integration.api.search;

import com.cashback.integration.api.search.configuration.SearchSpotifyConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(value = "searchSpotifyClient", url = "https://api.spotify.com/v1",
        configuration = SearchSpotifyConfiguration.class)
public interface SearchSpotifyClient {

    @RequestMapping(method = RequestMethod.GET, path = "/search")
    @ResponseBody
    SearchSpotifyResponse search(@RequestParam("q") String q, @RequestParam("search") String search,
                                 @RequestParam("type") String type, @RequestParam("limit") Integer limit);
}

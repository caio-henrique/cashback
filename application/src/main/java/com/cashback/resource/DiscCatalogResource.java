package com.cashback.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog/disc")
public class DiscCatalogResource {

    @GetMapping
    public List<Beer> all() {
        return beers.findAll();
    }
}

package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CheapSharkController {

    @GetMapping("/api/deals")
    public String getDeals() {

        String url = "https://www.cheapshark.com/api/1.0/deals?storeID=1&upperPrice=15";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, String.class);
    }
}
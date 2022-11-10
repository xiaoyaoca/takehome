package com.example.takehome.controller;

import com.example.takehome.response.CountryQueryResponse;
import com.example.takehome.service.TakehomeService;
import com.example.takehome.travelblades.TBContinent;
import com.google.common.util.concurrent.RateLimiter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/countries")
@AllArgsConstructor
@Slf4j
public class TakehomeController {
    private final RateLimiter rateLimiter;
    private final TakehomeService takehomeService;

    @GetMapping
    public ResponseEntity<CountryQueryResponse> getOtherCountries(@RequestParam String[] codes) {
        boolean isOk = rateLimiter.tryAcquire();
        if (isOk) {
            return new ResponseEntity<>(this.takehomeService.getOtherCountries(codes), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.TOO_MANY_REQUESTS);
    }
}

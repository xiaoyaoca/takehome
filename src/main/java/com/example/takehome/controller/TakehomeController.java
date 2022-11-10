package com.example.takehome.controller;

import com.example.takehome.response.CountryQueryResponse;
import com.example.takehome.service.TakehomeService;
import com.example.takehome.travelblades.TBContinent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final TakehomeService takehomeService;

    @GetMapping
    public CountryQueryResponse getOtherCountries(@RequestParam String[] codes) {
        return this.takehomeService.getOtherCountries(codes);
    }
}

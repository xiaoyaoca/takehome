package com.example.takehome.service;

import com.example.takehome.response.CountryQueryResponse;

public interface TakehomeService {
    public CountryQueryResponse getOtherCountries(String[] countries);
}

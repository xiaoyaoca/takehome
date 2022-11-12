package com.example.takehome.service.impl;

import com.example.takehome.config.TrevorbladesConfigProperties;
import com.example.takehome.model.CountryContinent;
import com.example.takehome.response.CountryQueryResponse;
import com.example.takehome.response.RespContinent;
import com.example.takehome.service.TakehomeService;
import com.example.takehome.travelblades.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class TakehomeServiceImpl implements TakehomeService {
    private final RestTemplate restTemplate;
    private final TrevorbladesConfigProperties trevorbladesConfigProperties;

    @Override
    public CountryQueryResponse getOtherCountries(String[] codes) {
        CountryQueryResponse resp = new CountryQueryResponse();
        Map<String, CountryContinent> continents = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> requestVariables = new HashMap<>();
        for (String code : codes) {
            requestVariables.put("arg1", code);
            HttpEntity<TGGraphQLRequest> request = new HttpEntity<>(new TGGraphQLRequest(trevorbladesConfigProperties.getCountryQuery(), requestVariables), headers);
            log.debug("query string: " + request.getBody().getQuery());
            TBCountryQueryResponse queryResponse = this.restTemplate.postForObject(this.trevorbladesConfigProperties.getUrl(), request, TBCountryQueryResponse.class);
            if (queryResponse != null && queryResponse.getData() != null) {
                TBCountry[] countries = queryResponse.getData().getCountries();
                if (countries != null && countries.length > 0) {
                    TBCountry country = countries[0];
                    String continentCode = country.getContinent().getCode();
                    CountryContinent continent = continents.get(continentCode);
                    if (continent == null) {
                        continent = new CountryContinent(continentCode, country.getContinent().getName());
                        continents.put(continentCode, continent);
                    }
                    continent.addCountry(country.getCode());
                } else {
                    log.warn("Travelblades country query response is empty for country code " + code);
                }
            } else {
                log.warn("Travelblades country query response is null for country code " + code);
            }
        }
        List<RespContinent> respContinents = new ArrayList<>(continents.size());
        for (CountryContinent continent : continents.values()) {
            requestVariables.put("arg1", continent.getContinentCode());
            HttpEntity<TGGraphQLRequest> request = new HttpEntity<>(new TGGraphQLRequest(trevorbladesConfigProperties.getContinentQuery(), requestVariables), headers);
            log.debug("query string: " + request.getBody().getQuery());
            TBContinentQueryResponse queryResponse = this.restTemplate.postForObject(this.trevorbladesConfigProperties.getUrl(), request, TBContinentQueryResponse.class);
            if (queryResponse != null && queryResponse.getData() != null) {
                TBContinent[] tbContinents = queryResponse.getData().getContinents();
                if (tbContinents != null && tbContinents.length > 0) {
                    TBContinent tbContinent = tbContinents[0];
                    RespContinent respContinent = new RespContinent(tbContinent.getName());
                    respContinent.addCountries(continent.getCountries());
                    if (continent.getCountries() != null) {
                        for (TBCountry tbCountry : tbContinent.getCountries()) {
                            if (!continent.hasCountry(tbCountry.getCode())) {
                                respContinent.addOtherCountry(tbCountry.getCode());
                            }
                        }
                    }
                    respContinents.add(respContinent);
                }
            } else {
                log.warn("Travelblades continent query response is null for continent code " + continent.getContinentCode());
            }
        }
        resp.setContinents(respContinents);
        return resp;
    }
}

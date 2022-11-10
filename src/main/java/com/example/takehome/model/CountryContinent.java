package com.example.takehome.model;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryContinent {
    private String continentCode;
    private String continentName;
    private Set<String> countries;

    public CountryContinent(String code, String name) {
        this.continentCode = code;
        this.continentName = name;
    }

    public void addCountry(String code) {
        if (this.countries == null)
            this.countries = new LinkedHashSet<>();
        this.countries.add(code);
    }

    public boolean hasCountry(String code) {
        if (this.countries == null)
            return false;
        else
            return this.countries.contains(code);
    }

}

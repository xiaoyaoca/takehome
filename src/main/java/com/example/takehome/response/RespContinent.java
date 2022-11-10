package com.example.takehome.response;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class RespContinent {
    private List<String> countries;
    @NonNull
    private String name;
    private List<String> otherCountries;

    public void addCountry(String code) {
        if (this.countries == null)
            this.countries = new ArrayList<>();
        this.countries.add(code);
    }

    public void addOtherCountry(String code) {
        if (this.otherCountries == null)
            this.otherCountries = new LinkedList<>();
        this.otherCountries.add(code);
    }

    public void addCountries(Collection<String> codes) {
        if (this.countries == null)
            this.countries = new ArrayList<>();
        this.countries.addAll(codes);
    }

}

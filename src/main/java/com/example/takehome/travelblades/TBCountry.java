package com.example.takehome.travelblades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBCountry {
    private String code;
    private String name;
    private TBContinent continent;
}

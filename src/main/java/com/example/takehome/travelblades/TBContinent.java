package com.example.takehome.travelblades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TBContinent {
    private String code;
    private String name;
    private List<TBCountry> countries;
}

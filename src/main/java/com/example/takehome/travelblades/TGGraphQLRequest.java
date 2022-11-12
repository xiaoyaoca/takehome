package com.example.takehome.travelblades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TGGraphQLRequest {
    private String query;
    private Map<String, Object> variables;
}

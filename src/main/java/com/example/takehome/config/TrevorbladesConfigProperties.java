package com.example.takehome.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "trevorblades.countries")
public class TrevorbladesConfigProperties {
    private String url;
    private String countryQuery;
    private String continentQuery;
}

package com.tosin.investire.client.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyProfileDto {

    private String symbol;
    private long mktCap;
    private String companyName;
    private String name;
    private String industry;
    private String website;
    private String description;
    private String sector;
    private String address;
    private String country;
    private String image;
    private String tagline;
    private String overview;
    private String background;
    private String technology;
    private boolean isActivelyTrading;


}

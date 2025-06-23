package com.tosin.investire.client.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetDetailDto {

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

    private double percentChange;
    private double volume;

    private int rank;

    @JsonProperty("market_data")
    private MarketData marketData;

    @JsonProperty("marketcap")
    private MarketCap marketCap;




    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MarketData {
        @JsonProperty("percent_change_usd_last_24_hours")
        private double percentChange;
        @JsonProperty("volume_last_24_hours")
        private double volume;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MarketCap {
        private int rank;
        @JsonProperty("current_marketcap_usd")
        private long cap;
    }





}

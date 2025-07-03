package com.tosin.investire.market.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalMarketData {

    @JsonProperty("total_market_cap")
    private GlobalMarketCap globalMarketCap;
    @JsonProperty("market_cap_change_percentage_24h_usd")
    private double marketCapPercentageChange;
    @JsonProperty("total_volume")
    private TotalVolume totalVolume;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GlobalMarketCap {

        private long usd;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TotalVolume {

        private long usd;
    }



}





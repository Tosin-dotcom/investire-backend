package com.tosin.investire.client.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tosin.investire.market.dto.GlobalMarketData;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinGeckoResponseDto {

    private GlobalMarketData data;

}

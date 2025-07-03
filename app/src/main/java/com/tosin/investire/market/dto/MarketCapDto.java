package com.tosin.investire.market.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarketCapDto {

    private long marketCap;
    private double percentChange;
    private long volume;

}

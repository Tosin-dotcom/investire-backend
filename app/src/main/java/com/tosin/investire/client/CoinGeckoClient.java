package com.tosin.investire.client;


import com.tosin.investire.client.dto.CoinGeckoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "coingeckoclient", url = "${coingecko.api-base-url}")
public interface CoinGeckoClient {

    @GetMapping("/global")
    CoinGeckoResponseDto getCryptoMarketCap(@RequestHeader("x-cg-demo-api-key") String apiKey);


}

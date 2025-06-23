package com.tosin.investire.client;


import com.tosin.investire.client.dto.MessariResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "messariclient", url = "${messari.api.base-url}")
public interface MessariClient {

    @GetMapping("/assets/{symbol}/profile")
    MessariResponseDto getCryptoInfo(@PathVariable("symbol") String symbol);

    @GetMapping("/assets/{symbol}/metrics")
    MessariResponseDto getCryptoMetric(@PathVariable("symbol") String symbol);

}

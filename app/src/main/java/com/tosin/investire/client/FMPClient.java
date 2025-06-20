package com.tosin.investire.client;


import com.tosin.investire.client.dto.CompanyProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "fmpclient", url = "${fmp.api.base-url}")
public interface FMPClient {

    @GetMapping("/profile/{symbol}")
    List<CompanyProfileDto> getCompanyProfile(@PathVariable("symbol") String symbol, @RequestParam("apikey") String apiKey);

}

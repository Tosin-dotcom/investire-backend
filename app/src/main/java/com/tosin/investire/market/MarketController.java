package com.tosin.investire.market;


import com.tosin.investire.client.dto.CompanyProfileDto;
import com.tosin.investire.commons.model.Response;
import com.tosin.investire.market.dto.MarketType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.tosin.investire.commons.Constants.MARKET_API_BASE_URL;

@RestController
@RequestMapping(MARKET_API_BASE_URL)
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @GetMapping("company-profile/{symbol}")
    public ResponseEntity<Response<CompanyProfileDto>> getCompanyProfile(@PathVariable String symbol, @RequestParam
            MarketType type) {

        CompanyProfileDto companyProfile = marketService.getCompanyProfile(type, symbol);
        Response<CompanyProfileDto> response = Response.<CompanyProfileDto>builder()
                .body(companyProfile)
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

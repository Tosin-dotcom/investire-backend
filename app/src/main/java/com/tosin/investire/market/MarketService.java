package com.tosin.investire.market;


import com.tosin.investire.client.FMPClient;
import com.tosin.investire.client.MessariClient;
import com.tosin.investire.client.dto.CompanyProfileDto;
import com.tosin.investire.commons.exception.InvestireException;
import com.tosin.investire.market.dto.MarketType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final FMPClient fmpClient;
    private final MessariClient messariClient;
    private final ModelMapper modelMapper;

    @Value("${fmp.api.key}")
    public String fmpApiKey;

    public CompanyProfileDto getCompanyProfile(MarketType marketType, String symbol) {

        if (MarketType.STOCK.equals(marketType)) {
            List<CompanyProfileDto> profiles = fmpClient.getCompanyProfile(symbol, fmpApiKey);
            if (profiles == null || profiles.isEmpty()) {
                throw new InvestireException(HttpStatus.BAD_REQUEST, "Invalid company symbol");
            }
            return profiles.get(0);
        } else {
            return messariClient.getCryptoInfo(symbol).getData();
        }
    }


}

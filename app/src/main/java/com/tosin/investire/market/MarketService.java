package com.tosin.investire.market;


import com.tosin.investire.client.CoinGeckoClient;
import com.tosin.investire.client.FMPClient;
import com.tosin.investire.client.MessariClient;
import com.tosin.investire.client.dto.AssetDetailDto;
import com.tosin.investire.client.dto.CoinGeckoResponseDto;
import com.tosin.investire.commons.exception.InvestireException;
import com.tosin.investire.market.dto.AssetType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {

    private final FMPClient fmpClient;
    private final MessariClient messariClient;
    private final CoinGeckoClient coinGeckoClient;

    @Value("${fmp.api.key}")
    public String fmpApiKey;

    public AssetDetailDto getAssetDetails(AssetType assetType, String symbol) {

        if (AssetType.STOCK.equals(assetType)) {
            List<AssetDetailDto> profiles = fmpClient.getCompanyProfile(symbol, fmpApiKey);
            if (profiles == null || profiles.isEmpty()) {
                throw new InvestireException(HttpStatus.BAD_REQUEST, "Invalid company symbol");
            }
            return profiles.get(0);
        } else {
            return getCryptoData(symbol);
        }
    }


    public CoinGeckoResponseDto getCryptoMarketCap() {

        return coinGeckoClient.getCryptoMarketCap();
    }

    private AssetDetailDto getCryptoData(String symbol) {

        AssetDetailDto cryptoProfile = messariClient.getCryptoInfo(symbol).getData();
        AssetDetailDto cryptoMetrics = messariClient.getCryptoMetric(symbol).getData();

        cryptoProfile.setPercentChange(cryptoMetrics.getMarketData().getPercentChange());
        cryptoProfile.setVolume(cryptoMetrics.getMarketData().getVolume());
        cryptoProfile.setRank(cryptoMetrics.getMarketCap().getRank());
        cryptoProfile.setMktCap(cryptoMetrics.getMarketCap().getCap());
        return cryptoProfile;
    }


}

package com.tosin.investire.market;


import com.tosin.investire.client.dto.AssetDetailDto;
import com.tosin.investire.commons.model.Response;
import com.tosin.investire.market.dto.AssetType;
import com.tosin.investire.market.dto.GlobalMarketData;
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

    @GetMapping("asset/{symbol}")
    public ResponseEntity<Response<AssetDetailDto>> getCompanyProfile(@PathVariable String symbol, @RequestParam
    AssetType type) {

        AssetDetailDto assetDetails = marketService.getAssetDetails(type, symbol);
        Response<AssetDetailDto> response = Response.<AssetDetailDto>builder()
                .body(assetDetails)
                .status(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("cap")
    public ResponseEntity<Response<GlobalMarketData>> getCryptoMarketCap(@RequestParam AssetType type) {

        GlobalMarketData globalMarketData = marketService.getCryptoMarketCap().getData();
        Response<GlobalMarketData> response = Response.<GlobalMarketData>builder()
                .body(globalMarketData)
                .status(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}

package com.stock_data.stock_data.service.impl;

import com.stock_data.stock_data.repository.StockRepository;
import com.stock_data.stock_data.service.StockService;
import com.stock_data.stock_data.utils.dto.StockResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final RestClient restClient;
    private String apiKey = "KET6LHvlzHnc5MXO9GSk00kxhr1kbGtg";
    private String baseUrl = "https://financialmodelingprep.com/api/v3/historical-price-full/";

    @Override
    public StockResponseDTO getStock(String symbol) {
        return restClient.get()
                .uri(UriComponentsBuilder.fromHttpUrl(baseUrl + symbol).toUriString(), uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .build())
                .retrieve()
                .body(StockResponseDTO.class);


//        return restClient.get()
//                .uri(uriBuilder -> uriBuilder
//                        .path(baseUrl + symbol)
//                        .queryParam("apikey", apiKey)
//                        .build()
//                )
//                .retrieve()
//                .body(new ParameterizedTypeReference<StockResponseDTO>() {});
    }
}

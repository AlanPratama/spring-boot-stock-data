package com.stock_data.stock_data.service.impl;

import com.stock_data.stock_data.entity.Result;
import com.stock_data.stock_data.entity.Stock;
import com.stock_data.stock_data.repository.ResultRepository;
import com.stock_data.stock_data.repository.StockRepository;
import com.stock_data.stock_data.service.StockService;
import com.stock_data.stock_data.utils.dto.ResultDTO;
import com.stock_data.stock_data.utils.dto.StockResponseDTO;
import exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ResultRepository resultRepository;
    private final RestClient restClient;
    private String apiKey = "KET6LHvlzHnc5MXO9GSk00kxhr1kbGtg";
    private String baseUrl = "https://financialmodelingprep.com/api/v3/historical-price-full/";

    @Override
    public StockResponseDTO getStock(String symbol) {
        if (stockRepository.findBySymbol(symbol).isEmpty()) {
            StockResponseDTO stockResponseDTO = restClient.get()
                    .uri(UriComponentsBuilder.fromHttpUrl(baseUrl + symbol).toUriString(), uriBuilder -> uriBuilder
                            .queryParam("apikey", apiKey)
                            .build())
                    .retrieve()
                    .body(StockResponseDTO.class);

            Stock stock = Stock.builder()
                    .symbol(stockResponseDTO.getSymbol())
                    .build();

            stockRepository.save(stock);

            List<ResultDTO> res = stockResponseDTO.getHistorical();

            for (var resultDTO : res) {
                Result result = Result.builder()
                        .date(resultDTO.getDate())
                        .high(resultDTO.getHigh())
                        .low(resultDTO.getLow())
                        .close(resultDTO.getClose())
                        .adjClose(resultDTO.getAdjClose())
                        .changeOvertime(resultDTO.getChangeOvertime())
                        .stock(stock)
                        .open(resultDTO.getOpen())
                        .volume(resultDTO.getVolume())
                        .unadjustedVolume(resultDTO.getUnadjustedVolume())
                        .change(resultDTO.getChange())
                        .changePercent(resultDTO.getChangePercent())
                        .vwap(resultDTO.getVwap())
                        .label(resultDTO.getLabel())
                        .build();

                resultRepository.save(result);
            }
            return stockResponseDTO;
        } else {
            Stock stock = stockRepository.findBySymbol(symbol).orElseThrow(() -> new NotFoundException("Stock Not Found!"));

            List<ResultDTO> resultDTOList = stock.getResults().stream()
                    .map(r -> ResultDTO.builder()
                            .date(r.getDate())
                            .open(r.getOpen())
                            .high(r.getHigh())
                            .low(r.getLow())
                            .close(r.getClose())
                            .adjClose(r.getAdjClose())
                            .volume(r.getVolume())
                            .unadjustedVolume(r.getUnadjustedVolume())
                            .change(r.getChange())
                            .changePercent(r.getChangePercent())
                            .vwap(r.getVwap())
                            .label(r.getLabel())
                            .changeOvertime(r.getChangeOvertime())
                            .build()).collect(Collectors.toList());

            StockResponseDTO stockResponseDTO = new StockResponseDTO();
            stockResponseDTO.setSymbol(stock.getSymbol());
            stockResponseDTO.setHistorical(resultDTOList);

            return stockResponseDTO;
        }
    }
}

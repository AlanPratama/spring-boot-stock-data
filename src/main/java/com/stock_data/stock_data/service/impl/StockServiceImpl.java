package com.stock_data.stock_data.service.impl;

import com.stock_data.stock_data.entity.Result;
import com.stock_data.stock_data.entity.Stock;
import com.stock_data.stock_data.mapper.MapToResult;
import com.stock_data.stock_data.mapper.MapToResultDTO;
import com.stock_data.stock_data.repository.ResultRepository;
import com.stock_data.stock_data.repository.StockRepository;
import com.stock_data.stock_data.service.StockService;
import com.stock_data.stock_data.utils.dto.RecommendResponseDTO;
import com.stock_data.stock_data.utils.dto.ResultDTO;
import com.stock_data.stock_data.utils.dto.StockResponseDTO;
import exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
                            .queryParam("from", LocalDate.now().minusDays(30))
                            .queryParam("apikey", apiKey)
                            .build())
                    .retrieve()
                    .body(StockResponseDTO.class);

            Stock stock = Stock.builder()
                    .symbol(stockResponseDTO.getSymbol())
                    .build();

            stockRepository.save(stock);

            List<ResultDTO> res = stockResponseDTO.getHistorical();

            for (int i=0;i<10;i++) {
                Result result = MapToResult.convertToResult(res.get(i), stock);

                resultRepository.save(result);
            }
            return stockResponseDTO;
        } else {
            Stock stock = stockRepository.findBySymbol(symbol).orElseThrow(() -> new NotFoundException("Stock Not Found!"));

            List<ResultDTO> resultDTOList = stock.getResults().stream()
                    .map(MapToResultDTO::convertToResultDTO).collect(Collectors.toList());

            StockResponseDTO stockResponseDTO = new StockResponseDTO();
            stockResponseDTO.setSymbol(stock.getSymbol());
            stockResponseDTO.setHistorical(resultDTOList);

            return stockResponseDTO;
        }
    }

    @Override
    public RecommendResponseDTO getRecommend(String symbol) {
        Stock stock = stockRepository.findBySymbol(symbol).orElseThrow(()-> new NotFoundException("Stock Not Found"));

        List<ResultDTO> restDTOList = stock.getResults().stream()
                .map(MapToResultDTO::convertToResultDTO).toList();

        Double Mean = 0.0;
        for (var res : restDTOList){
            Mean = Mean + res.getAdjClose();
        }

        Mean = Mean/10;

        if (Mean>restDTOList.get(9).getAdjClose()){
            return RecommendResponseDTO.builder()
                    .symbol(symbol)
                    .date(LocalDate.now())
                    .action("BUY")
                    .MA(Mean)
                    .AdjClose(restDTOList.get(9).getAdjClose())
                    .build();
        } else if(Mean==restDTOList.get(9).getAdjClose()){
            return RecommendResponseDTO.builder()
                    .symbol(symbol)
                    .date(LocalDate.now())
                    .action("Hold")
                    .MA(Mean)
                    .AdjClose(restDTOList.get(9).getAdjClose())
                    .build();
        } else {
            return RecommendResponseDTO.builder()
                    .symbol(symbol)
                    .date(LocalDate.now())
                    .action("Sell")
                    .MA(Mean)
                    .AdjClose(restDTOList.get(9).getAdjClose())
                    .build();
        }
    }

    @Override
    public List<RecommendResponseDTO> getAllRecommend() {
        List<Stock> stocks = stockRepository.findAll();
        List<RecommendResponseDTO> listOfRecomend = new ArrayList<>(stocks.size()) ;
        for (var stock : stocks){
            listOfRecomend.add(getRecommend(stock.getSymbol()));;
        }
        return listOfRecomend;
    }
}
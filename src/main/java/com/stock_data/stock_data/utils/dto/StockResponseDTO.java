package com.stock_data.stock_data.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stock_data.stock_data.entity.Result;

import java.util.List;

public class StockResponseDTO {
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("historical")
    private List<ResultDTO> historical;
}

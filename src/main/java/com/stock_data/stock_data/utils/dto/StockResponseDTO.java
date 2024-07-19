package com.stock_data.stock_data.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stock_data.stock_data.entity.Result;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StockResponseDTO {
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("historical")
    private List<ResultDTO> historical;
}

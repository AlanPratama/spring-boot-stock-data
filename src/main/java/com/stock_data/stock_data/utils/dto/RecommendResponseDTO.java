package com.stock_data.stock_data.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class RecommendResponseDTO {
    @JsonProperty("Symbol")
    private String symbol;

    @JsonProperty("Date")
    private Date date;

    @JsonProperty("Action")
    private String action; // Buy / Sell
}

package com.stock_data.stock_data.utils.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class RecommendResponseDTO {
    @JsonProperty("Symbol")
    private String symbol;

    @JsonProperty("Date")
    private LocalDate date;

    @JsonProperty("Action")
    private String action; // Buy / Sell
    // Rata rata > nilai kemarin == BUY
    // Rata rata < nilai kemarin == SELL

    @JsonProperty("MA")
    private Double MA;

    @JsonProperty("AdjClose")
    private Double AdjClose;
}

package com.stock_data.stock_data.utils.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stock_data.stock_data.entity.Stock;
import jakarta.persistence.*;

import java.util.Date;

public class ResultDTO {
    @JsonProperty("date")
    private Date date;

    @JsonProperty("open")
    private Double open;

    @JsonProperty("high")
    private Double high;

    @JsonProperty("low")
    private Double low;

    @JsonProperty("close")
    private Double close;

    @JsonProperty("adjClose")
    private Double adjClose;

    @JsonProperty("volume")
    private Integer volume;

    @JsonProperty("unadjustedVolume")
    private Integer unadjustedVolume;

    @JsonProperty("change")
    private Double change;

    @JsonProperty("changePercent")
    private Double changePercent;

    @JsonProperty("vwap")
    private Double vwap;

    @JsonProperty("label")
    private String label;

    @JsonProperty("changeOverTime")
    private Double changeOvertime;
}

package com.stock_data.stock_data.mapper;

import com.stock_data.stock_data.entity.Result;
import com.stock_data.stock_data.utils.dto.ResultDTO;

import java.util.stream.Collectors;

public class MapToResultDTO {

    public static ResultDTO convertToResultDTO(Result r) {
        return ResultDTO.builder()
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
                .build();
    }

}

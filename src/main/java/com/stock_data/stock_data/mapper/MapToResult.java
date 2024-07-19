package com.stock_data.stock_data.mapper;

import com.stock_data.stock_data.entity.Result;
import com.stock_data.stock_data.entity.Stock;
import com.stock_data.stock_data.utils.dto.ResultDTO;

public class MapToResult {

    public static Result convertToResult(ResultDTO resultDTO, Stock stock) {
        return Result.builder()
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
    }

}

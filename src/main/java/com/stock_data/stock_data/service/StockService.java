package com.stock_data.stock_data.service;

import com.stock_data.stock_data.entity.Stock;
import com.stock_data.stock_data.utils.dto.StockResponseDTO;

import java.util.Optional;

public interface StockService {
    StockResponseDTO getStock(String symbol);

}

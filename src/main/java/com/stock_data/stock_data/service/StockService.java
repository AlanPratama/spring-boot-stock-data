package com.stock_data.stock_data.service;

import com.stock_data.stock_data.utils.dto.StockResponseDTO;

public interface StockService {

    StockResponseDTO getStock(String symbol);

}

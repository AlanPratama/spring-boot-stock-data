package com.stock_data.stock_data.repository;

import com.stock_data.stock_data.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}

package com.stock_data.stock_data.repository;

import com.stock_data.stock_data.entity.Stock;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    @EntityGraph(attributePaths = "results")
    Optional<Stock> findBySymbol(String symbol);
}

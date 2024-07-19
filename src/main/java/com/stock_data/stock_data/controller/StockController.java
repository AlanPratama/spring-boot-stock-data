package com.stock_data.stock_data.controller;

import com.stock_data.stock_data.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService service;

    @GetMapping("/{symbol}")
    public ResponseEntity<?> getStock(@PathVariable("symbol") String symbol) {
        return ResponseEntity.ok(service.getStock(symbol));
    }


    @GetMapping("/{symbol}/recommend")
    public ResponseEntity<?> getRecommend(@PathVariable("symbol") String symbol) {
        return ResponseEntity.ok(service.getRecommend(symbol));
    }

    @GetMapping("/recommend")
    public ResponseEntity<?> getAllRecommend() {
        return ResponseEntity.ok(service.getAllRecommend());
    }
}

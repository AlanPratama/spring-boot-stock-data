package com.stock_data.stock_data.repository;

import com.stock_data.stock_data.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Integer> {
}

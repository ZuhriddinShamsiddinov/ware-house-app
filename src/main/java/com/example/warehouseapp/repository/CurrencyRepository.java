package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    List<Currency> findAllByActiveTrue();
}

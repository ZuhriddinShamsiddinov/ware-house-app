package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse add(Currency currency) {
        currencyRepository.save(currency);
        return new ApiResponse("Saved", true);
    }
}

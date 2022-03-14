package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.InputDTO;
import com.example.warehouseapp.entity.Input;
import com.example.warehouseapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ProductRepository productRepository;

    public ApiResponse saveInput(InputDTO inputDTO) {
        Input input = new Input();
        return new ApiResponse("Added", true);
    }

}

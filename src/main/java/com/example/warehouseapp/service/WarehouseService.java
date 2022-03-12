package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.entity.Warehouse;
import com.example.warehouseapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse add(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
        return new ApiResponse("Saved", true);
    }
}

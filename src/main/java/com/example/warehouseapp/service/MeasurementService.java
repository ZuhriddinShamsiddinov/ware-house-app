package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.entity.Measurement;
import com.example.warehouseapp.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;


    public ApiResponse add(Measurement measurement) {
        measurementRepository.save(measurement);
        return new ApiResponse("Saved", true);
    }
}

package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findAllByActiveTrue();
}

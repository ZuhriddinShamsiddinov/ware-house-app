package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.InputProduct;
import com.example.warehouseapp.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OutputPrroductRepository extends JpaRepository<OutputProduct, UUID> {
    Optional<OutputProduct> findByOutput_Id(UUID id);
}

package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OutputPrroductRepository extends JpaRepository<OutputProduct, UUID> {
}

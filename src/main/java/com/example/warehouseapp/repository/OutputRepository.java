package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Output;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OutputRepository extends JpaRepository<Output, UUID> {
    List<Output> findAllByActiveTrue();
}

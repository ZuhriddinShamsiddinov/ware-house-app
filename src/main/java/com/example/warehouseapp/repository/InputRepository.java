package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InputRepository extends JpaRepository<Input, UUID> {
    List<Input> findAllByActiveTrue();
}

package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginRepository extends JpaRepository<Admin, Integer> {
}

package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findAllByActiveTrue();
}

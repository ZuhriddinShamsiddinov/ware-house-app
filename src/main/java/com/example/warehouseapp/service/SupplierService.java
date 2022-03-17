package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.entity.Supplier;
import com.example.warehouseapp.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public ApiResponse create(Supplier supplier) {
        supplierRepository.save(supplier);
        return new ApiResponse("saved", true);
    }

    public ApiResponse update(Integer id, Supplier supplier) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Xatolik", false);
        Supplier supplier1 = byId.get();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(supplier1);
        return new ApiResponse("saved", true);
    }

    public ApiResponse delete(Integer id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse("Xatolik", false);
        Supplier supplier = byId.get();
        supplier.setActive(false);
        supplierRepository.save(supplier);
        return new ApiResponse("delete", true);
    }

}

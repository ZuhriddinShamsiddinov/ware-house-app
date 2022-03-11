package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.UserDTO;
import com.example.warehouseapp.entity.User;
import com.example.warehouseapp.entity.Warehouse;
import com.example.warehouseapp.repository.UserRepository;
import com.example.warehouseapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse save(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        if (!userRepository.existsByPhoneNumber(userDTO.getPhoneNumber())) return new ApiResponse("Telefon bor", false);
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCode(UUID.randomUUID().toString());
        List<Warehouse> warehouseById = warehouseRepository.findAllById(userDTO.getWarehousesId());
        user.setWarehouseList(warehouseById);
        userRepository.save(user);
        return new ApiResponse("Saved", true);
    }
}

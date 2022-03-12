package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.UserDTO;
import com.example.warehouseapp.entity.User;
import com.example.warehouseapp.entity.Warehouse;
import com.example.warehouseapp.repository.UserRepository;
import com.example.warehouseapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse save(UserDTO userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        if (!userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new ApiResponse("Phone Number is taken", false);
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setCode(UUID.randomUUID().toString());
        List<Warehouse> warehousesById = warehouseRepository.findAllById(Collections.singleton(userDto.getWarehouseId()));
        user.setWarehouseList(warehousesById);
        User save = userRepository.save(user);
        System.out.println(save);
        return new ApiResponse("Saved!", true);
    }

    public ApiResponse edit(Integer id, UserDTO userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) return new ApiResponse("Not Found", false);
        User user = optionalUser.get();
        if (!userDto.getName().isEmpty()) user.setName(userDto.getName());
        if (!userDto.getLastName().isEmpty()) user.setLastName(userDto.getLastName());
        if (!userDto.getPassword().isEmpty()) user.setPassword(userDto.getPassword());
        if (!userDto.getPhoneNumber().isEmpty() && !userDto.getPhoneNumber().equals(user.getPhoneNumber())) {
            if (!userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
                return new ApiResponse("Phone Number is taken", false);
            user.setPhoneNumber(userDto.getPhoneNumber());
        }
        user.setCode(UUID.randomUUID().toString());
        List<Warehouse> warehouseList = warehouseRepository.findAllById(Collections.singleton(userDto.getWarehouseId()));
        user.setWarehouseList(warehouseList);
        userRepository.save(user);
        return new ApiResponse("Edited!", true);
    }
}

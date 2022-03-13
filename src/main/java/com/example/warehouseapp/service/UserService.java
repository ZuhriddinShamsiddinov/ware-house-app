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
import java.util.Optional;
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
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setCode(UUID.randomUUID().toString());
        user.setPassword(userDTO.getPassword());
        List<Warehouse> warehousesById = warehouseRepository.findAllById(userDTO.getWarehousesId());
        user.setWarehouseList(warehousesById);
        User save = userRepository.save(user);
        System.out.println(save);
        return new ApiResponse("Saved!", true);
    }

    public ApiResponse edit(Integer id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(user.getPassword());
        user.setCode(UUID.randomUUID().toString());
        List<Warehouse> warehouseList = warehouseRepository.findAllById(userDTO.getWarehousesId());
        user.setWarehouseList(warehouseList);
        userRepository.save(user);
        return new ApiResponse("Edited!", true);
    }
}

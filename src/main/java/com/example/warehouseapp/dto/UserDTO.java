package com.example.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserDTO {

    private String name;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private List<Integer> warehousesId;


}

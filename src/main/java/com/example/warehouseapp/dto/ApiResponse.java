package com.example.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ApiResponse {
    private String message;
    private  Boolean seccess;
    private Object object;

    public ApiResponse(String message, Boolean seccess) {
        this.message = message;
        this.seccess = seccess;
    }
}
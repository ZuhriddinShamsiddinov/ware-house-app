package com.example.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputProductDTO {
    private Integer productId;
    private double price;
    private double amount;
    private Date expireDate;
}

package com.example.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputProductDTO {

    private Integer productId;

    private double price;

    private double amount;
}

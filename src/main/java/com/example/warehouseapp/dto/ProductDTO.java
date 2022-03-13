package com.example.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private Integer categoryId;
    private String code;
    private Integer measurementId;
    private Boolean active;

    public ProductDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}


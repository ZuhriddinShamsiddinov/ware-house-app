package com.example.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InputDTO {
    private Date date;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    List<InputProductDTO> inputProducts;
}

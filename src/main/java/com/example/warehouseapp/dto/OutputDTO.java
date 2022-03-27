package com.example.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer warehouseId;

    private Integer clientId;

    private Integer currencyId;

    private List<OutputProductDTO> outputProducts;
}

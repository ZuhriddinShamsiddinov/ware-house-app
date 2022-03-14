package com.example.warehouseapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputDTO {


    private Date date;

    private Integer warehouseId;

    private Integer clientId;

    private Integer currencyId;

    private List<OutputDTOProduct> outputDTOProductList;
}

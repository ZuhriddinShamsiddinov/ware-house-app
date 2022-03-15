package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.InputDTO;
import com.example.warehouseapp.dto.InputProductDTO;
import com.example.warehouseapp.entity.*;
import com.example.warehouseapp.repository.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputProductRepository inputProductRepository;

    @SneakyThrows
    public ApiResponse saveInput(InputDTO inputDTO) {
        Input input = new Input();
        input.setCode(UUID.randomUUID().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(inputDTO.getDate());
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(format);
        input.setDate(date1);
        input.setFactureNumber(UUID.randomUUID().toString());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new ApiResponse("NOT", false);
        input.setCurrency(optionalCurrency.get());


        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (optionalSupplier.isEmpty()) return new ApiResponse("NOT", false);
        input.setSupplier(optionalSupplier.get());


        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new ApiResponse("NOT", false);
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);


        List<InputProductDTO> inputProductDTOList = inputDTO.getInputProductDTOList();

        for (InputProductDTO inputProductDTO : inputProductDTOList) {
            InputProduct inputProduct = new InputProduct();
            inputProduct.setAmount(inputProductDTO.getAmount());
            inputProduct.setPrice(inputProductDTO.getPrice());

            inputProduct.setExpireDate(inputProductDTO.getExpireDate());

            Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
            if (optionalProduct.isEmpty()) return new ApiResponse("NOT", false);
            inputProduct.setProduct(optionalProduct.get());

            inputProduct.setInput(input);
            inputProductRepository.save(inputProduct);
        }
        return new ApiResponse("Save", true);
    }

}

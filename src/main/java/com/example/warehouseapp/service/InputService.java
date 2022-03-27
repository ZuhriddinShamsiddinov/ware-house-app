package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.InputDTO;
import com.example.warehouseapp.dto.InputProductDTO;
import com.example.warehouseapp.entity.*;
import com.example.warehouseapp.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InputService {

    final
    InputRepository inputRepository;
    final
    WarehouseRepository warehouseRepository;
    final
    SupplierRepository supplierRepository;
    final
    CurrencyRepository currencyRepository;
    final
    ProductRepository productRepository;
    final
    InputProductRepository inputProductRepository;

    @SneakyThrows
    public void saveInput(InputDTO inputDTO) {
        Input input = new Input();
        input.setCode(UUID.randomUUID().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(inputDTO.getDate());
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(format);
        input.setDate(date1);
        input.setFactureNumber(UUID.randomUUID().toString());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (optionalCurrency.isEmpty()) {
            new ApiResponse("NOT", false);
            return;
        }
        input.setCurrency(optionalCurrency.get());


        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (optionalSupplier.isEmpty()) {
            new ApiResponse("NOT", false);
            return;
        }
        input.setSupplier(optionalSupplier.get());


        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (optionalWarehouse.isEmpty()) {
            new ApiResponse("NOT", false);
            return;
        }
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);


        List<InputProductDTO> inputProductDTOList = inputDTO.getInputProducts();

        for (InputProductDTO inputProductDTO : inputProductDTOList) {
            InputProduct inputProduct = new InputProduct();
            inputProduct.setAmount(inputProductDTO.getAmount());
            inputProduct.setPrice(inputProductDTO.getPrice());

            inputProduct.setExpireDate(inputProductDTO.getExpireDate());

            Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
            if (optionalProduct.isEmpty()) {
                new ApiResponse("NOT", false);
                return;
            }
            inputProduct.setProduct(optionalProduct.get());

            inputProduct.setInput(input);
            inputProductRepository.save(inputProduct);
        }
        new ApiResponse("Save", true);
    }


}

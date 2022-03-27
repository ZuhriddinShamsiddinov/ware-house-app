package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.OutputProductDTO;
import com.example.warehouseapp.dto.OutputDTO;
import com.example.warehouseapp.entity.*;
import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OutputService {

    final OutputRepository outputRepository;
    final ProductRepository productRepository;
    final ClientRepository clientRepository;
    final WarehouseRepository warehouseRepository;
    final CurrencyRepository currencyRepository;
    final OutputPrroductRepository outputPrroductRepository;

    @SneakyThrows
    public ApiResponse add(OutputDTO dto) {
        Output output = new Output();
        output.setCode(UUID.randomUUID().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(dto.getDate());
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(format);
        output.setDate(date1);
        output.setFactureNumber(UUID.randomUUID().toString());

        Optional<Currency> optionalCurrency = currencyRepository.findById(dto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new ApiResponse("NOT", false);
        output.setCurrency(optionalCurrency.get());


        Optional<Client> optionalClient = clientRepository.findById(dto.getClientId());
        if (optionalClient.isEmpty()) return new ApiResponse("NOT", false);
        output.setClient(optionalClient.get());


        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(dto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new ApiResponse("NOT", false);
        output.setWarehouse(optionalWarehouse.get());
        outputRepository.save(output);


        List<OutputProductDTO> outputProductDTOList = dto.getOutputProducts();

        for (OutputProductDTO outputProductDTO : outputProductDTOList) {
            OutputProduct outputProduct = new OutputProduct();
            outputProduct.setAmount(outputProductDTO.getAmount());
            outputProduct.setPrice(outputProductDTO.getPrice());


            Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
            if (optionalProduct.isEmpty()) return new ApiResponse("NOT", false);
            outputProduct.setProduct(optionalProduct.get());

            outputProduct.setOutput(output);
            outputPrroductRepository.save(outputProduct);
        }
        return new ApiResponse("Save", true);
    }
}

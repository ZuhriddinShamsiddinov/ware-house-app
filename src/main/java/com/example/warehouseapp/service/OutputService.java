package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.OutputDTO;
import com.example.warehouseapp.dto.OutputProductDTO;
import com.example.warehouseapp.entity.*;
import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    OutputPrroductRepository outputPrroductRepository;

    public ApiResponse add(OutputDTO dto) {
        Output output = new Output();
        output.setCode(UUID.randomUUID().toString());
        output.setDate(dto.getDate());
        output.setFactureNumber(UUID.randomUUID().toString());

        Optional<Currency> optionalCurrency = currencyRepository.findById(dto.getCurrencyId());
        output.setCurrency(optionalCurrency.get());
        Optional<Client> optionalClient = clientRepository.findById(dto.getClientId());
        output.setClient(optionalClient.get());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(dto.getWarehouseId());
        output.setWarehouse(optionalWarehouse.get());

        List<OutputProductDTO> outputProductDTOS = dto.getOutputProducts();

        List<OutputProduct> outputProducts = new ArrayList<>();

        for (OutputProductDTO outputProductDTO : outputProductDTOS) {
            OutputProduct outputProduct = new OutputProduct();
            Optional<Product> byId = productRepository.findById(outputProductDTO.getProductId());

            outputProduct.setProduct(byId.get());
            outputProduct.setPrice(outputProductDTO.getPrice());
            outputProduct.setAmount(outputProductDTO.getAmount());
            outputProduct.setOutput(output);
            outputProducts.add(outputProduct);

        }



        outputRepository.save(output);
        return new ApiResponse("Added", true, output);
    }
}

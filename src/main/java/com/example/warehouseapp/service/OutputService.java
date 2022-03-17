package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.OutputDTO;
import com.example.warehouseapp.dto.OutputProductDTO;
import com.example.warehouseapp.entity.*;
import com.example.warehouseapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
    public ApiResponse addoutput(OutputDTO outputDTO) throws ParseException {
        Output output = new Output();
        output.setCode(UUID.randomUUID().toString());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(output.getDate());
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(format);
        output.setDate(date1);
        output.setFactureNumber(UUID.randomUUID().toString());


        Optional<Client> byId = clientRepository.findById(outputDTO.getClientId());
        if (byId.isEmpty())return new ApiResponse("Eror",false);
        output.setClient(byId.get());

        Optional<Warehouse> byId1 = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (byId1.isEmpty())return new ApiResponse("Eror",false);
        output.setWarehouse(byId1.get());

        Optional<Currency> byId2 = currencyRepository.findById(outputDTO.getCurrencyId());
        if (byId2.isEmpty())return new ApiResponse("Eror",false);
        output.setCurrency(byId2.get());


        for (OutputProductDTO outputProductDTO : outputDTO.getOutputProductDTOList()) {
            OutputProduct outputProduct=new OutputProduct();
            outputProduct.setAmount(outputProductDTO.getAmount());
            outputProduct.setPrice(outputProductDTO.getPrice());



            Optional<Product> byId3 = productRepository.findById(outputProductDTO.getProductId());
            if (byId3.isEmpty())return new ApiResponse("Eror",false);
            outputProduct.setProduct(byId3.get());
            outputProduct.setOutput(output);
            outputPrroductRepository.save(outputProduct);

        }
        return new ApiResponse("Save",true);

    }
}

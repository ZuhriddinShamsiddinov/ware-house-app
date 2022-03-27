package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.DashboardDTO;
import com.example.warehouseapp.entity.Input;
import com.example.warehouseapp.entity.InputProduct;
import com.example.warehouseapp.entity.Output;
import com.example.warehouseapp.entity.OutputProduct;
import com.example.warehouseapp.repository.InputProductRepository;
import com.example.warehouseapp.repository.InputRepository;
import com.example.warehouseapp.repository.OutputPrroductRepository;
import com.example.warehouseapp.repository.OutputRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DashboardService {
    final InputRepository inputRepository;
    final OutputRepository outputRepository;
    final InputProductRepository inputProductRepository;
    final OutputPrroductRepository outputPrroductRepository;

    public List<DashboardDTO> getAll() {
        List<Input> inputList = inputRepository.findAllByActiveTrue();
        List<Output> outputList = outputRepository.findAllByActiveTrue();
        List<DashboardDTO> dashboardDTOList = new ArrayList<>();
        for (Input input : inputList) {
            for (Output output : outputList) {

                Optional<InputProduct> productOptional = inputProductRepository.findByInputId(input.getId());
                InputProduct inputProduct = productOptional.get();
                Optional<OutputProduct> productOptional1 = outputPrroductRepository.findByOutput_Id(output.getId());
                OutputProduct outputProduct = productOptional1.get();

                if (outputProduct.getProduct().equals(inputProduct.getProduct())) {
                    DashboardDTO dashboardDTO = new DashboardDTO();
                    dashboardDTO.setProductName(inputProduct.getProduct().getName());
                    dashboardDTO.setAmount(inputProduct.getAmount() - outputProduct.getAmount());
                    dashboardDTO.setSum(productOptional.get().getPrice() * outputProduct.getAmount());
                    dashboardDTOList.add(dashboardDTO);
                }

            }
        }
        return dashboardDTOList;
    }
}

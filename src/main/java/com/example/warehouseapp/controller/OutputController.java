package com.example.warehouseapp.controller;

import com.example.warehouseapp.dto.OutputDTO;
import com.example.warehouseapp.entity.Output;
import com.example.warehouseapp.repository.*;
import com.example.warehouseapp.service.OutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/output")
@RequiredArgsConstructor
public class OutputController {

    final OutputRepository outputRepository;
    final OutputService outputService;
    final ProductRepository productRepository;
    final ClientRepository clientRepository;
    final WarehouseRepository warehouseRepository;
    final CurrencyRepository currencyRepository;
    final OutputPrroductRepository outputPrroductRepository;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("list", outputRepository.findAllByActiveTrue());
        return "output/output";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("productList", productRepository.findAllByActiveTrue());
        model.addAttribute("clientList", clientRepository.findAllByActiveTrue());
        model.addAttribute("warehouseList", warehouseRepository.findAllByActiveTrue());
        model.addAttribute("currencyList", currencyRepository.findAllByActiveTrue());
        model.addAttribute("today", LocalDate.now());
        return "output/output-add";
    }

    @PostMapping("/add")
    public String saveOutput(@ModelAttribute OutputDTO outputDTO) {
        outputService.add(outputDTO);
        return "redirect:/output";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        Output output = outputOptional.get();
        output.setActive(false);
        outputRepository.save(output);
        return "redirect:/output";
    }

}

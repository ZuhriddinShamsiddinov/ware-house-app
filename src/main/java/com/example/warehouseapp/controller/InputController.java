package com.example.warehouseapp.controller;


import com.example.warehouseapp.dto.InputDTO;
import com.example.warehouseapp.entity.Input;
import com.example.warehouseapp.repository.*;
import com.example.warehouseapp.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    InputService inputService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    InputProductRepository inputProductRepository;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("list", inputRepository.findAllByActiveTrue());
        model.addAttribute("inputProductList", inputProductRepository.findAll());
        return "input/input";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("productList", productRepository.findAllByActiveTrue());
        model.addAttribute("supplierList", supplierRepository.findAllByActiveTrue());
        model.addAttribute("warehouseList", warehouseRepository.findAllByActiveTrue());
        model.addAttribute("currencyList", currencyRepository.findAllByActiveTrue());
        model.addAttribute("today", LocalDate.now().toString());
        return "input/input-add";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute InputDTO inputDTO) {
        inputService.saveInput(inputDTO);
        return "redirect:/input";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        Input input = inputOptional.get();
        input.setActive(false);
        inputRepository.save(input);
        return "redirect:/input";
    }


}

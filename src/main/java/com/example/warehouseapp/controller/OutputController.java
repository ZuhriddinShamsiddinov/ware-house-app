package com.example.warehouseapp.controller;

import com.example.warehouseapp.dto.OutputDTO;
import com.example.warehouseapp.repository.*;
import com.example.warehouseapp.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping("/output")

public class OutputController {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    OutputService outputService;
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
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("outputList",outputRepository.findAll());
        return "output/output";
    }

    @GetMapping("/add")
    public String getAddPage(Model model){
        model.addAttribute("ptoductList",productRepository.findAllByActiveTrue());
        model.addAttribute("clientList",clientRepository.findAllByActiveTrue());
        model.addAttribute("warehouseList",warehouseRepository.findAllByActiveTrue());
        model.addAttribute("currencyList",currencyRepository.findAllByActiveTrue());
        return "output/addd-output";
    }
    @PostMapping("/add")
    public String saveoutput(@ModelAttribute OutputDTO outputDTO) throws ParseException {
        outputService.addoutput(outputDTO);
        return "redirect:/output";
    }

}

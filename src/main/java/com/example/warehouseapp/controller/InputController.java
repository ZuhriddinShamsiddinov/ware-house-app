package com.example.warehouseapp.controller;


import com.example.warehouseapp.repository.InputRepository;
import com.example.warehouseapp.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/input")
public class InputController {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    InputService inputService;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("list", inputRepository.findAll());
        return "input/input";
    }


}

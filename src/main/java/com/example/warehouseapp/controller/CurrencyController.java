package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.entity.Measurement;
import com.example.warehouseapp.repository.CurrencyRepository;
import com.example.warehouseapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/currency")
public class CurrencyController {
    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("list", currencyRepository.findAllByActiveTrue());
        return "currency/currency";
    }


    @GetMapping("/add")
    public String addPage() {
        return "currency/currency-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("warehouse") Currency currency) {
        currencyService.add(currency);
        return "redirect:/currency";
    }


    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        Currency currency = currencyOptional.get();
        model.addAttribute("currency", currency);
        return "currency/currency-edit";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute Measurement currency) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        Currency currency1 = currencyOptional.get();
        currency1.setId(currency.getId());
        currency1.setName(currency.getName());
        currencyRepository.save(currency1);
        return "redirect:/currency";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Currency> byId = currencyRepository.findById(id);
        Currency currency = byId.get();
        currency.setActive(false);
        currencyRepository.save(currency);
        return "redirect:/currency";
    }

}

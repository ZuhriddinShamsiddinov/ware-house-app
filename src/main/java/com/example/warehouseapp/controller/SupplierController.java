package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Supplier;
import com.example.warehouseapp.repository.SupplierRepository;
import com.example.warehouseapp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;
    @Autowired
    SupplierRepository supplierRepository;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("supplierlist", supplierRepository.findAllByActiveTrue());
        return "supplier/supplier";
    }

    @GetMapping("/add")
    public String addSupplier() {
        return "supplier/add-supplier";
    }

    @PostMapping("/add")
    public String addSupplier(@ModelAttribute Supplier supplier) {
        supplierService.create(supplier);
        return "redirect:/supplier";
    }

    @GetMapping("/edit/{id}")
    public String editSupplier(Model model, @PathVariable Integer id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        Supplier supplier = byId.get();
        if (byId.isEmpty()) return "Xatolik";
        model.addAttribute("list", supplier);
        return "supplier/edit-supplier";

    }

    @PostMapping("/edit/{id}")
    public String editSupplierSave(@PathVariable Integer id, @ModelAttribute Supplier supplier) {
        supplierService.update(id, supplier);
        return "redirect:/supplier";
    }


    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Supplier> byId = supplierRepository.findById(id);
        if (byId.isEmpty()) return "Bunday id yo'q";
        Supplier supplier = byId.get();
        supplier.setActive(false);
        supplierRepository.save(supplier);
        return "redirect:/supplier";
    }

}

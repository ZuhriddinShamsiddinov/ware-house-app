package com.example.warehouseapp.controller;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.entity.Supplier;
import com.example.warehouseapp.repository.SupplierRepository;
import com.example.warehouseapp.service.SupplierServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
@Autowired
    SupplierServise supplierServise;
@Autowired
    SupplierRepository supplierRepository;
@GetMapping
    public String getAll(Model model){
    model.addAttribute("supplierlist",supplierRepository.findAllByActiveTrue());
    return "supplier/supplier";
}
@GetMapping("/add")
public String addSupplier(){
    return "supplier/add-supplier";
}
@PostMapping("/add")
    public String addsupplier(@ModelAttribute Supplier supplier){
    supplierServise.create(supplier);
    return "redirect:/supplier";
}
@GetMapping("/edit/{id}")
    public String editSupplier(Model model,@PathVariable Integer id){
    Optional<Supplier> byId = supplierRepository.findById(id);
    if (!byId.isPresent()) return "Xatolik";
    model.addAttribute("list",supplierRepository.findById(id));
    return "supplier/edit-supplier";

}
@PostMapping("/edit/{id}")
    public String editSupplierSave(@PathVariable Integer id,@ModelAttribute Supplier supplier){
    ApiResponse updete = supplierServise.updete(id, supplier);
    return "redirect:/suplier";
}

}

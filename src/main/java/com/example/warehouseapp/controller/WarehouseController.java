package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Warehouse;
import com.example.warehouseapp.repository.WarehouseRepository;
import com.example.warehouseapp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @Autowired
    WarehouseRepository warehouseRepository;


    @GetMapping
    public String getWarehouses(Model model) {
        List<Warehouse> warehouseList = warehouseRepository.findAllByActiveTrue();
        model.addAttribute("list", warehouseList);
        return "warehouse/warehouse";
    }

    @GetMapping("/add")
    public String addPage() {
        return "warehouse/warehouse-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("warehouse") Warehouse warehouse) {
        warehouseService.add(warehouse);
        return "redirect:/warehouse";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        Warehouse warehouse = warehouseOptional.get();
        model.addAttribute("warehouse", warehouse);
        return "warehouse/warehouse-edit";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute Warehouse warehouse) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        Warehouse warehouse1 = warehouseOptional.get();
        warehouse1.setId(warehouse.getId());
        warehouse1.setName(warehouse.getName());
        warehouseRepository.save(warehouse1);
        return "redirect:/warehouse";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Warehouse> byId = warehouseRepository.findById(id);
        Warehouse warehouse = byId.get();
        warehouse.setActive(false);
        warehouseRepository.save(warehouse);
        return "redirect:/warehouse";
    }
}

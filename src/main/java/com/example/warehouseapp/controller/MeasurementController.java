package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Measurement;
import com.example.warehouseapp.repository.MeasurementRepository;
import com.example.warehouseapp.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    MeasurementService measurementService;

    @GetMapping
    public String measurements(Model model) {
        model.addAttribute("list", measurementRepository.findAllByActiveTrue());
        return "measurement/measurement";
    }

    @GetMapping("/add")
    public String addPage() {
        return "measurement/measurement-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("warehouse") Measurement measurement) {
        measurementService.add(measurement);
        return "redirect:/measurement";
    }


    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        Measurement measurement = measurementOptional.get();
        model.addAttribute("measurement", measurement);
        return "measurement/measurement-edit";
    }

    @PostMapping("edit/{id}")
    public String update(@PathVariable(value = "id") Integer id, @ModelAttribute Measurement measurement) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        Measurement measurement1 = measurementOptional.get();
        measurement1.setId(measurement.getId());
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);
        return "redirect:/measurement";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        Measurement measurement = byId.get();
        measurement.setActive(false);
        measurementRepository.save(measurement);
        return "redirect:/measurement";
    }
}

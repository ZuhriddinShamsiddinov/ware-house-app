package com.example.warehouseapp.controller;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    final DashboardService dashboardService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("list", dashboardService.getAll());
        return "dashboard/dashboard";
    }
}

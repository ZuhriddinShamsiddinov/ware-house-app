package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Admin;
import com.example.warehouseapp.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    final LoginRepository loginRepository;

    @GetMapping
    public String loginPage() {
        return "home/home";
    }

    @PostMapping
    public String loginCheck(@ModelAttribute Admin admin) {
        List<Admin> adminList = loginRepository.findAll();
        for (Admin isAdmin : adminList) {
            if (isAdmin.getUserName().equals(admin.getPassword()) && isAdmin.getUserName().equals(admin.getPassword()))
                return "home/home";
        }
        return "error";

    }
}

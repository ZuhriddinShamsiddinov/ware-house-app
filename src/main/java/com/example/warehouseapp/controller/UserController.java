package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.User;
import com.example.warehouseapp.repository.UserRepository;
import com.example.warehouseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public String getUsers(Model model) {
        List<User> userList = userRepository.findAllByActiveTrue();
        model.addAttribute("users", userList);
        return "user/user";
    }

}

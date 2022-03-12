package com.example.warehouseapp.controller;

import com.example.warehouseapp.dto.UserDTO;
import com.example.warehouseapp.entity.User;
import com.example.warehouseapp.entity.Warehouse;
import com.example.warehouseapp.repository.UserRepository;
import com.example.warehouseapp.repository.WarehouseRepository;
import com.example.warehouseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    @GetMapping
    public String getUsers(Model model) {
        List<User> userList = userRepository.findAllByActiveTrue();
        model.addAttribute("list", userList);
        return "user/user";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        List<Warehouse> allByActiveTrue = warehouseRepository.findAllByActiveTrue();
        model.addAttribute("warehouses", allByActiveTrue);
        return "user/user-add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/user";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        user.setActive(false);
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("edit/{id}")
    public String editPage(Model model, @PathVariable Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        List<Warehouse> warehouseList = warehouseRepository.findAllByActiveTrue();
        model.addAttribute("warehouses", warehouseList);
        model.addAttribute("user", user);
        return "user/user-edit";
    }

    @PostMapping("edit/{id}")
    public String update(@ModelAttribute UserDTO userDTO, @PathVariable Integer id) {
        userService.edit(id, userDTO);
        return "redirect:/user";
    }


}

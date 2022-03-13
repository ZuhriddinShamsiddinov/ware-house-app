package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Client;
import com.example.warehouseapp.repository.ClientRepository;
import com.example.warehouseapp.service.ClientService;
import jdk.jfr.StackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("listclient",clientRepository.findAll());
        return "client/client";
    }
    @GetMapping("/add")
    public String addClient(){
        return "client/add-client";
    }
    @PostMapping("/add")
    public String addsave(@ModelAttribute Client client){
        clientService.create(client);
        return "redirect:/client";
    }
    @GetMapping("/edit/{id}")
    public String updete(Model model, @PathVariable Integer id){
        model.addAttribute("editlist",clientRepository.findById(id));
        return "client/edit-client";
    }
    @PostMapping("/edit/{id}")
    public String editsave(@PathVariable Integer id,@ModelAttribute Client client){
        clientService.updete(id,client);
        return "redirect:/client";
    }


}

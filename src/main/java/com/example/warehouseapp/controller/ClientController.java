package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Client;
import com.example.warehouseapp.repository.ClientRepository;
import com.example.warehouseapp.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("listclient", clientRepository.findAllByActiveTrue());
        return "client/client";
    }

    @GetMapping("/add")
    public String addClient() {
        return "client/add-client";
    }

    @PostMapping("/add")
    public String addsave(@ModelAttribute Client client) {
        clientService.create(client);
        return "redirect:/client";
    }

    @GetMapping("/edit/{id}")
    public String updete(Model model, @PathVariable Integer id) {
        Optional<Client> byId = clientRepository.findById(id);
        Client client = byId.get();
        model.addAttribute("editlist", client);
        return "client/edit-client";
    }

    @PostMapping("/edit/{id}")
    public String editsave(@PathVariable Integer id, @ModelAttribute Client client) {
        clientService.update(id, client);
        return "redirect:/client";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isEmpty()) return "Bunday id yo'q";
        Client client = byId.get();
        client.setActive(false);
        clientRepository.save(client);
        return "redirect:/client";
    }

}

package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.entity.Client;
import com.example.warehouseapp.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ApiResponse create(Client client){
        Client save = clientRepository.save(client);
        return new ApiResponse("save",true,save);
    }
    public ApiResponse update(Integer id,Client client){
        Optional<Client> byId = clientRepository.findById(id);
        if (byId.isEmpty()){ return new ApiResponse("Xatolik",false);}
        Client client1 = byId.get();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        Client save = clientRepository.save(client1);
        return new ApiResponse("saved",true);
    }
}

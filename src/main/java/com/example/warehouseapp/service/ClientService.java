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

        Client client1=new Client();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        Client save = clientRepository.save(client1);
        return new ApiResponse("save",true);
    }
    public ApiResponse updete(Integer id,Client client){
        Optional<Client> byId = clientRepository.findById(id);
        if (!byId.isPresent()){ return new ApiResponse("Xatolik",false);}
        Client client1 = byId.get();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        Client save = clientRepository.save(client1);
        return new ApiResponse("saved",true);
    }
}

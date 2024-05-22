package com.canalha.boilerplatewebflux.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.canalha.boilerplatewebflux.api.services.ClientService;
import com.canalha.boilerplatewebflux.shared.entities.Client;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final  ClientService  clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    
    @GetMapping("/first")
    public Mono<Client> findFirstClient() {
        return clientService.findFirstClient();
    }
}

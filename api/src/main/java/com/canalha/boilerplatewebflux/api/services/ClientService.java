package com.canalha.boilerplatewebflux.api.services;
import com.canalha.boilerplatewebflux.shared.entities.Client;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    public Mono<Client> findFirstClient() {
        Client cl = new Client();
        cl.setId("afafsfhjhhd-ndhc-dccceuu");
        return Mono.just(cl);
    }
}

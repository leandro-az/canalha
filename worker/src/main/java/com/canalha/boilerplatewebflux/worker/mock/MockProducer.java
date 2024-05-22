package com.canalha.boilerplatewebflux.worker.mock;

import com.canalha.boilerplatewebflux.shared.entities.Client;
import com.canalha.boilerplatewebflux.worker.service.ReactiveConsumerService;
import com.canalha.boilerplatewebflux.worker.service.ReactiveProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@ConditionalOnProperty(name = "application.mode", havingValue = "producer")
public class MockProducer {


    @Autowired
    private final ReactiveProducerService reactiveProducerService;

    public MockProducer(ReactiveProducerService reactiveProducerService) {
        this.reactiveProducerService = reactiveProducerService;
    }

     @EventListener(ApplicationStartedEvent.class)
    public void sendManyToTopic(){

        Client element = new Client();

        for (int i = 0; i <10 ; i++) {
            element.setId("element"+"-"+i);
            this.reactiveProducerService.send(element);
        }
    }


}
package com.canalha.boilerplatewebflux.worker.listener;


import com.canalha.boilerplatewebflux.shared.entities.Client;
import com.canalha.boilerplatewebflux.worker.service.ReactiveConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

@Component
@ConditionalOnProperty(name = "application.mode", havingValue = "consumer")
public class KafkaConsumer {

    private final ReactiveConsumerService reactiveConsumerService;

    @Autowired
    public KafkaConsumer(ReactiveConsumerService reactiveConsumerService) {
        this.reactiveConsumerService = reactiveConsumerService;
    }


    @EventListener(ApplicationStartedEvent.class)
    public Disposable start() {
        return this.reactiveConsumerService.consume();
    }


}
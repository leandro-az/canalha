package com.canalha.boilerplatewebflux.worker.service;

import com.canalha.boilerplatewebflux.shared.entities.Client;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.kafka.sender.SenderOptions;

@Service
public class ReactiveProducerService {

    private final Logger log = LoggerFactory.getLogger(ReactiveProducerService.class);
    private final ReactiveKafkaProducerTemplate<String, Client> reactiveKafkaProducerTemplate;

    @Value("${kafka.client.name}")
    private String topic;

    public ReactiveProducerService(ReactiveKafkaProducerTemplate<String, Client> producer) {
        this.reactiveKafkaProducerTemplate = producer;
    }

    public void send(Client Client) {
        log.info("send to topic={}, {}={},", topic, Client.class.getSimpleName(), Client);
        reactiveKafkaProducerTemplate.send(topic, Client)
                .doOnSuccess(senderResult -> log.info("sent {} offset : {}", Client, senderResult.recordMetadata().offset()))
                .subscribe();
    }
}

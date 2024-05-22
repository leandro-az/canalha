package com.canalha.boilerplatewebflux.worker.config;


import com.canalha.boilerplatewebflux.shared.entities.Client;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import reactor.kafka.sender.SenderOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ReactiveKafkaProducerConfig {

    @Bean
    public ReactiveKafkaProducerTemplate<String, Client> reactiveKafkaProducer(KafkaProperties properties) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        return new ReactiveKafkaProducerTemplate<String, Client>(SenderOptions.create(props));
    }
}

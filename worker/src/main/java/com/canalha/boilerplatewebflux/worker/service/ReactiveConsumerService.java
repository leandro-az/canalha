package com.canalha.boilerplatewebflux.worker.service;
import com.canalha.boilerplatewebflux.shared.entities.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.ReceiverOffset;
import reactor.kafka.receiver.ReceiverRecord;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReactiveConsumerService {

    private final ReactiveKafkaConsumerTemplate<String, Client> reactiveKafkaConsumer;

    public Disposable consume() {
        Flux<ReceiverRecord<String, Client>> kafkaFlux = this.reactiveKafkaConsumer.receive();
        return kafkaFlux.subscribe(record -> {
            ReceiverOffset offset = record.receiverOffset();
            System.out.printf("Received message: topic-partition=%s offset=%d key=%s value=%s\n",
                    offset.topicPartition(),
                    offset.offset(),
                    record.key(),
                    record.value());
            offset.acknowledge();
        });
    }
}
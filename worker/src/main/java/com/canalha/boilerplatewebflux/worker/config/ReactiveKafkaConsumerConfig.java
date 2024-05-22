package com.canalha.boilerplatewebflux.worker.config;
import com.canalha.boilerplatewebflux.shared.entities.Client;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import reactor.kafka.receiver.ReceiverOptions;

@Configuration
public class ReactiveKafkaConsumerConfig {
    @Value("${kafka.client.name}")
    private String topic;

    @Bean
    public ReceiverOptions<String, Client> kafkaReceiver(KafkaProperties kafkaProperties) {

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "reactive-kafka");
        config.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        config.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE,"com.canalha.boilerplatewebflux.shared.entities.Client");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ReceiverOptions<String, Client> basicReceiverOptions = ReceiverOptions.create(config);
        return basicReceiverOptions.subscription(Collections.singletonList(this.topic));

    }

    @Bean
    public ReactiveKafkaConsumerTemplate<String, Client> reactiveKafkaConsumer(ReceiverOptions<String, Client> kafkaReceiverOptions) {
        return new ReactiveKafkaConsumerTemplate<String, Client>(kafkaReceiverOptions);
    }
}

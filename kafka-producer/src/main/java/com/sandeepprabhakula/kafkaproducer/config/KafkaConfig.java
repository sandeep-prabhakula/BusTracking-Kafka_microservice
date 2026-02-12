package com.sandeepprabhakula.kafkaproducer.config;

import com.sandeepprabhakula.kafkaproducer.model.BusPayload;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
//    @Value("${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapServer;

    @Bean
    public NewTopic topic() {

        return TopicBuilder
                .name(AppConstants.LOCATION_UPDATE_TOPIC)
                .partitions(6)
//                .replicas(2)
                .build();
    }

//    @Bean
//    public ProducerFactory<String, BusPayload> producerFactory() {
//        Map<String, Object> config = new HashMap<>();
//
//
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
//                bootstrapServer);
//        config.put(
//                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class);
//        config.put(
//                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                JsonSerializer.class);
//
//
////        Flush config
//        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
//        config.put(ProducerConfig.ACKS_CONFIG, "all");
//        config.put(ProducerConfig.LINGER_MS_CONFIG, 0);
//        config.put(ProducerConfig.BATCH_SIZE_CONFIG, 16535);
//        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
//
//        return new DefaultKafkaProducerFactory<>(config);
//    }
//
//    @Bean
//    public KafkaTemplate<String, BusPayload> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }

}

package com.sandeepprabhakula.kafkaproducer.service;

import com.sandeepprabhakula.kafkaproducer.config.AppConstants;
import com.sandeepprabhakula.kafkaproducer.model.BusPayload;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.network.Send;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, BusPayload> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    public void update(BusPayload busPayload) throws ExecutionException, InterruptedException {

        CompletableFuture<SendResult<String, BusPayload>> future = kafkaTemplate.send(AppConstants.LOCATION_UPDATE_TOPIC,
                busPayload.getBusNumber(), busPayload);
        SendResult<String,BusPayload>metadata = future.get();
        logger.info("Data loaded into: {} partition.", metadata.getRecordMetadata().partition());

        logger.info("message: location produced");

    }
}

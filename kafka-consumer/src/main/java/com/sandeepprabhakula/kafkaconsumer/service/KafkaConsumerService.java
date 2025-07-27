package com.sandeepprabhakula.kafkaconsumer.service;

import com.sandeepprabhakula.kafkaconsumer.config.AppConstants;
import com.sandeepprabhakula.kafkaconsumer.model.BusPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerService {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topicPartitions = @TopicPartition(topic = AppConstants.LOCATION_UPDATE_TOPIC,
            partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")),
            groupId = AppConstants.GROUP_ID
//            ,containerFactory = "kafkaListenerContainerFactory"
    )
    public void updateLocation(BusPayload value, @Header(KafkaHeaders.RECEIVED_KEY) String key) throws IOException {
        System.out.println(value);
        System.out.println(key);
        System.out.println("______________________________________");
        messagingTemplate.convertAndSend("/topic/updates", value);
    }
}

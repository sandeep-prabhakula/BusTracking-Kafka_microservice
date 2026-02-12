package com.sandeepprabhakula.kafkaconsumer.service;

import com.sandeepprabhakula.kafkaconsumer.config.AppConstants;
import com.sandeepprabhakula.kafkaconsumer.model.BusPayload;
import com.sandeepprabhakula.kafkaconsumer.model.Coordinates;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ConcurrentLinkedQueue<BusPayload> messageQueue = new ConcurrentLinkedQueue<>();
    private final ConcurrentHashMap<String, Coordinates> previousState = new ConcurrentHashMap<>();

    public KafkaConsumerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topics = AppConstants.LOCATION_UPDATE_TOPIC,
            groupId = AppConstants.GROUP_ID
            ,containerFactory = "kafkaListenerContainerFactory"
    )
    public void updateLocation(BusPayload value, @Header(KafkaHeaders.RECEIVED_KEY) String key) throws IOException {
        System.out.println(value);
        System.out.println(key);
        previousState.put(key, value.getCoordinates());
        System.out.println("______________________________________");
        System.out.println(value.getCoordinates().toString());
//Socket Config
                messagingTemplate.convertAndSend("/topic/updates/"+value.getBusNumber(), value);

//        messageQueue.add(value);

    }
    public ConcurrentLinkedQueue<BusPayload> getMessageQueue(){
        return messageQueue;
    }

    public BusPayload getCoordinatesByBusNumber(String busNumber){
        Coordinates coordinates = previousState.get(busNumber);
        BusPayload busPayload = new BusPayload();
        busPayload.setBusNumber(busNumber);
        busPayload.setCoordinates(coordinates);
        return busPayload;
    }
}

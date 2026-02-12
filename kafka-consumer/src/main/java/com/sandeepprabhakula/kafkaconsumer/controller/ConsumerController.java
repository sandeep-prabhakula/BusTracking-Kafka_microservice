package com.sandeepprabhakula.kafkaconsumer.controller;

import com.sandeepprabhakula.kafkaconsumer.config.KafkaConfig;
import com.sandeepprabhakula.kafkaconsumer.model.BusPayload;
import com.sandeepprabhakula.kafkaconsumer.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin("*")
public class ConsumerController {

    @Autowired
    private KafkaConfig kafkaConfig;

    @Autowired
    private KafkaConsumerService kafkaConsumerService;
    @GetMapping("/get-messages")
    public List<BusPayload> getMessages(){
        return new ArrayList<>(kafkaConsumerService.getMessageQueue());
    }

    @GetMapping("/get-by-bus/{id}")
    public BusPayload getMessageById(@PathVariable("id")String id){
        return kafkaConsumerService.getCoordinatesByBusNumber(id);
    }

}

package com.sandeepprabhakula.kafkaproducer.controller;

import com.sandeepprabhakula.kafkaproducer.model.BusPayload;
import com.sandeepprabhakula.kafkaproducer.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin("*")
@RequestMapping("/producer")
public class KafkaController {
    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation(@RequestBody BusPayload busPayload) throws ExecutionException, InterruptedException {
        kafkaService.update(busPayload);

        return new ResponseEntity<>(Map.of("message", "location Updated"), HttpStatus.OK);
    }
}

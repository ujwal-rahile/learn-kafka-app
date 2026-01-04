package com.kafka.producer.controller;

import com.kafka.producer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.kafka.avro.MigrationEvent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;

    @org.springframework.beans.factory.annotation.Value("${app.topic.fallout}")
    private String falloutTopic;

    @org.springframework.beans.factory.annotation.Value("${app.topic.migration}")
    private String migrationTopic;

    @GetMapping("/publish/fallout/{message}")
    public ResponseEntity<?> publishFallout(@PathVariable String message) {
        try {
            publisher.sendMessageToTopic(message, falloutTopic);
            return ResponseEntity.ok("Message published to Fallout successfully ..");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error posting message: " + ex.getMessage());
        }
    }

    @PostMapping("/publish/migration")
    public ResponseEntity<?> publishMigration(@RequestBody MigrationEvent message) {
        try {
            publisher.sendMessageToTopic(message, migrationTopic);
            return ResponseEntity.ok("Message published to Migration successfully ..");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error posting message: " + ex.getMessage());
        }
    }
}

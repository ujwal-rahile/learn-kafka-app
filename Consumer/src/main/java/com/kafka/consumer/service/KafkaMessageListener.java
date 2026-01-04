package com.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.avro.MigrationEvent;

@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
    private java.util.List<com.kafka.avro.Fruit> fruits = new java.util.ArrayList<>();

    @KafkaListener(topics = "${app.topic.fallout}", groupId = "jt-group-1")
    public void consumeFallout(String message) {
        log.info("Consumer consumed from Fallout: {}", message);
    }

    @KafkaListener(topics = "${app.topic.migration}", groupId = "jt-group-1")
    public void consumeMigration(MigrationEvent message) {
        log.info("Consumer consumed from Migration: {}", message);
        if (message.getData() != null && message.getData().getFruits() != null) {
            for (com.kafka.avro.Fruit incomingFruit : message.getData().getFruits()) {
                boolean exists = false;
                for (com.kafka.avro.Fruit existingFruit : fruits) {
                    if (existingFruit.getName().equals(incomingFruit.getName())) {
                        existingFruit.setColor(incomingFruit.getColor());
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    fruits.add(incomingFruit);
                }
            }
            log.info("Total fruits collected so far: {}", fruits.size());
            log.info("Fruits list: {}", fruits);
        }
    }
}

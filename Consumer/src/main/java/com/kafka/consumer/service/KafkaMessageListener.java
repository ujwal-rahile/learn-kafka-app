package com.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.avro.MigrationEvent;

@Service
public class KafkaMessageListener {

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "${app.topic.fallout}", groupId = "jt-group-1")
    public void consumeFallout(String message) {
        log.info("Consumer consumed from Fallout: {}", message);
    }

    @KafkaListener(topics = "${app.topic.migration}", groupId = "jt-group-1")
    public void consumeMigration(MigrationEvent message) {
        log.info("Consumer consumed from Migration: {}", message);
    }
}

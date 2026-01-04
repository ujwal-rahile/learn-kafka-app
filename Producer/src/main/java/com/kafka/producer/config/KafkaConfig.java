package com.kafka.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @org.springframework.beans.factory.annotation.Value("${app.topic.fallout}")
    private String falloutTopic;

    @org.springframework.beans.factory.annotation.Value("${app.topic.migration}")
    private String migrationTopic;

    @Bean
    public NewTopic createFalloutTopic() {
        return TopicBuilder.name(falloutTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic createMigrationTopic() {
        return TopicBuilder.name(migrationTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}

package com.kafka.consumer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MigrationEvent {
    private String source;
    private String target;
    private MigrationData data;
}

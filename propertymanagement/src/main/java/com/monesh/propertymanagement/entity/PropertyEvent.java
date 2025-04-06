package com.monesh.propertymanagement.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("property_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyEvent {
    @Id
    private String id;
    private String type;
    private String referenceId;
    private Instant timestamp;
}
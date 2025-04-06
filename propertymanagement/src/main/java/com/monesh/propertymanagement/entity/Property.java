package com.monesh.propertymanagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "properties")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {
    @Id
    private String id;
    private String propertyName;
    private String area;
    private double rentalPrice;
    private double currentValue;
    private boolean occupied;
    private String propertyManagerId;
}

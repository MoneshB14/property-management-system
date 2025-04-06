package com.monesh.propertymanagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "propertyManagers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyManager {
    @Id
    private String id;
    private String name;
    private String password;
}

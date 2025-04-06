package com.monesh.propertymanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monesh.propertymanagement.entity.PropertyEvent;

public interface EventRepository extends MongoRepository<PropertyEvent, String> {
}

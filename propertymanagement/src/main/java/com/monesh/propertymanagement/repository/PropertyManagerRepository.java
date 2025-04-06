package com.monesh.propertymanagement.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monesh.propertymanagement.entity.PropertyManager;

public interface PropertyManagerRepository extends MongoRepository<PropertyManager, String> {
    Optional<PropertyManager> findByName(String name);

    Optional<PropertyManager> findByNameAndPassword(String name, String password);
}
package com.monesh.propertymanagement.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.monesh.propertymanagement.entity.Property;

public interface PropertyRepository extends MongoRepository<Property, String> {
    List<Property> findByOccupiedTrue();

    List<Property> findByArea(String area);

    List<Property> findByPropertyManagerId(String managerId);
}
package com.monesh.propertymanagement.query.handler;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.monesh.propertymanagement.dto.PropertyDTO;
import com.monesh.propertymanagement.entity.Property;
import com.monesh.propertymanagement.repository.PropertyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetLowestValuedPropertyQueryHandler {

    private final PropertyRepository propertyRepo;

    public PropertyDTO handle() {
        Property property = propertyRepo.findAll()
                .stream()
                .min(Comparator.comparingDouble(Property::getCurrentValue))
                .orElseThrow(() -> new RuntimeException("No properties found"));

        return new PropertyDTO(
                property.getPropertyName(),
                property.getArea(),
                property.getRentalPrice(),
                property.getCurrentValue(),
                property.isOccupied(),
                null,
                null);
    }
}

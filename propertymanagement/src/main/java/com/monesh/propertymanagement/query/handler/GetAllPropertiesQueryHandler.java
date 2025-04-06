package com.monesh.propertymanagement.query.handler;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monesh.propertymanagement.dto.PropertyDTO;
import com.monesh.propertymanagement.entity.Property;
import com.monesh.propertymanagement.repository.PropertyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetAllPropertiesQueryHandler {
    private final PropertyRepository repo;

    public List<PropertyDTO> handle() {
        return repo.findAll().stream().map(this::toDTO).toList();
    }

    private PropertyDTO toDTO(Property p) {
        return new PropertyDTO(p.getPropertyName(), p.getArea(), p.getRentalPrice(),
                p.getCurrentValue(), p.isOccupied(), null, null);
    }
}

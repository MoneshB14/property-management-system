package com.monesh.propertymanagement.query.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.monesh.propertymanagement.dto.PropertyDTO;
import com.monesh.propertymanagement.entity.Property;
import com.monesh.propertymanagement.repository.PropertyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GetOccupiedPropertiesQueryHandler {

    private final PropertyRepository propertyRepo;

    public List<PropertyDTO> handle() {
        return propertyRepo.findByOccupiedTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PropertyDTO toDTO(Property p) {
        return new PropertyDTO(
                p.getPropertyName(),
                p.getArea(),
                p.getRentalPrice(),
                p.getCurrentValue(),
                p.isOccupied(),
                null,
                null);
    }
}

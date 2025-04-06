package com.monesh.propertymanagement.command.handler;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.monesh.propertymanagement.command.model.UpdatePropertyCommand;
import com.monesh.propertymanagement.dto.PropertyDTO;
import com.monesh.propertymanagement.entity.Property;
import com.monesh.propertymanagement.entity.PropertyEvent;
import com.monesh.propertymanagement.entity.PropertyManager;
import com.monesh.propertymanagement.repository.EventRepository;
import com.monesh.propertymanagement.repository.PropertyManagerRepository;
import com.monesh.propertymanagement.repository.PropertyRepository;
import com.monesh.propertymanagement.utils.EncryptionDecryptionUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdatePropertyCommandHandler {
    private final PropertyRepository propertyRepo;
    private final PropertyManagerRepository managerRepo;
    private final EventRepository eventRepo;
    private final EncryptionDecryptionUtils encryptionUtils;

    public void handle(UpdatePropertyCommand cmd) {
        PropertyDTO dto = cmd.dto();

        Property property = propertyRepo.findById(cmd.propertyId())
                .orElseThrow(() -> new RuntimeException("Property not found"));

        String encryptedPassword;
        try {
            encryptedPassword = encryptionUtils.encrypt(dto.getManagerPassword());
        } catch (Exception e) {
            throw new RuntimeException("Password encryption failed", e);
        }

        PropertyManager manager = managerRepo.findByNameAndPassword(dto.getManagerName(), encryptedPassword)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!property.getPropertyManagerId().equals(manager.getId())) {
            throw new RuntimeException("Unauthorized");
        }

        property.setRentalPrice(dto.getRentalPrice());
        property.setCurrentValue(dto.getCurrentValue());
        property.setOccupied(dto.isOccupied());

        propertyRepo.save(property);
        eventRepo.save(new PropertyEvent(null, "PROPERTY_UPDATED", property.getId(), Instant.now()));
    }
}
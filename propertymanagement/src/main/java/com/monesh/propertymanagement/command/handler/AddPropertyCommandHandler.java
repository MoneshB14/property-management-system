package com.monesh.propertymanagement.command.handler;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.monesh.propertymanagement.command.model.AddPropertyCommand;
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
public class AddPropertyCommandHandler {
    private final PropertyRepository propertyRepo;
    private final PropertyManagerRepository managerRepo;
    private final EventRepository eventRepo;
    private final EncryptionDecryptionUtils encryptionUtils;

    public void handle(AddPropertyCommand cmd) {
        PropertyDTO dto = cmd.dto();

        String encryptedPassword;
        try {
            encryptedPassword = encryptionUtils.encrypt(dto.getManagerPassword());
        } catch (Exception e) {
            throw new RuntimeException("Password encryption failed", e);
        }

        PropertyManager manager = managerRepo.findByName(dto.getManagerName())
                .orElseGet(() -> managerRepo
                        .save(new PropertyManager(null, dto.getManagerName(), encryptedPassword)));

        Property property = new Property(null, dto.getPropertyName(), dto.getArea(),
                dto.getRentalPrice(), dto.getCurrentValue(), dto.isOccupied(), manager.getId());

        Property saved = propertyRepo.save(property);

        eventRepo.save(new PropertyEvent(null, "PROPERTY_ADDED", saved.getId(), Instant.now()));
    }
}
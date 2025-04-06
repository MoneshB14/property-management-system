package com.monesh.propertymanagement.command.handler;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.monesh.propertymanagement.command.model.CalculateSalaryCommand;
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
public class CalculateSalaryCommandHandler {
    private final PropertyRepository propertyRepo;
    private final PropertyManagerRepository managerRepo;
    private final EventRepository eventRepo;
    private final EncryptionDecryptionUtils encryptionUtils;

    public double handle(CalculateSalaryCommand cmd) {
        String encryptedPassword;
        try {
            encryptedPassword = encryptionUtils.encrypt(cmd.password());
        } catch (Exception e) {
            throw new RuntimeException("Password encryption failed", e);
        }

        PropertyManager manager = managerRepo.findByNameAndPassword(cmd.name(), encryptedPassword)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        double total = propertyRepo.findByPropertyManagerId(manager.getId())
                .stream()
                .mapToDouble(Property::getRentalPrice)
                .sum() * 0.10;

        eventRepo.save(new PropertyEvent(null, "SALARY_CALCULATED", manager.getId(), Instant.now()));
        return total;
    }
}

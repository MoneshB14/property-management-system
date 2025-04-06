package com.monesh.propertymanagement.command.model;

import com.monesh.propertymanagement.dto.PropertyDTO;

public record UpdatePropertyCommand(String propertyId, PropertyDTO dto) {
}

package com.monesh.propertymanagement.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.monesh.propertymanagement.command.handler.AddPropertyCommandHandler;
import com.monesh.propertymanagement.command.handler.CalculateSalaryCommandHandler;
import com.monesh.propertymanagement.command.handler.UpdatePropertyCommandHandler;
import com.monesh.propertymanagement.command.model.AddPropertyCommand;
import com.monesh.propertymanagement.command.model.CalculateSalaryCommand;
import com.monesh.propertymanagement.command.model.UpdatePropertyCommand;
import com.monesh.propertymanagement.dto.PropertyDTO;
import com.monesh.propertymanagement.query.handler.GetAllPropertiesQueryHandler;
import com.monesh.propertymanagement.query.handler.GetLowestValuedPropertyQueryHandler;
import com.monesh.propertymanagement.query.handler.GetOccupiedPropertiesQueryHandler;
import com.monesh.propertymanagement.query.handler.GetPropertiesByAreaQueryHandler;
import com.monesh.propertymanagement.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final AddPropertyCommandHandler addHandler;
    private final UpdatePropertyCommandHandler updateHandler;
    private final CalculateSalaryCommandHandler salaryHandler;

    private final GetAllPropertiesQueryHandler allQueryHandler;
    private final GetOccupiedPropertiesQueryHandler occupiedQueryHandler;
    private final GetPropertiesByAreaQueryHandler areaQueryHandler;
    private final GetLowestValuedPropertyQueryHandler lowValueHandler;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> add(@RequestBody @Valid PropertyDTO dto) {
        addHandler.handle(new AddPropertyCommand(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Property added successfully", null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<String>> update(@PathVariable String id, @RequestBody @Valid PropertyDTO dto) {
        updateHandler.handle(new UpdatePropertyCommand(id, dto));
        return ResponseEntity.ok(new ApiResponse<>("Property updated successfully", null));
    }

    @PostMapping("/salary")
    public ResponseEntity<ApiResponse<Double>> salary(@RequestBody PropertyDTO dto) {
        double salary = salaryHandler.handle(
                new CalculateSalaryCommand(dto.getManagerName(), dto.getManagerPassword()));
        return ResponseEntity.ok(new ApiResponse<>("Salary calculated", salary));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> all() {
        return ResponseEntity.ok(new ApiResponse<>("All properties fetched", allQueryHandler.handle()));
    }

    @GetMapping("/occupied")
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> occupied() {
        return ResponseEntity.ok(new ApiResponse<>("Occupied properties fetched", occupiedQueryHandler.handle()));
    }

    @GetMapping("/area")
    public ResponseEntity<ApiResponse<List<PropertyDTO>>> byArea(@RequestParam String area) {
        return ResponseEntity.ok(new ApiResponse<>("Properties in area: " + area, areaQueryHandler.handle(area)));
    }

    @GetMapping("/lowest-value")
    public ResponseEntity<ApiResponse<PropertyDTO>> lowest() {
        return ResponseEntity.ok(new ApiResponse<>("Lowest valued property", lowValueHandler.handle()));
    }
}

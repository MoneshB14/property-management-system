package com.monesh.propertymanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    @NotBlank(message = "Property name is required")
    private String propertyName;

    @NotBlank(message = "Area is required")
    private String area;

    @Positive(message = "Rental price must be positive")
    private double rentalPrice;

    @PositiveOrZero(message = "Current value must be zero or positive")
    private double currentValue;

    private boolean occupied;

    @NotBlank(message = "Manager name is required")
    private String managerName;

    @NotBlank(message = "Manager password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*]).{5,20}$", message = "Password must contain at least one digit, one special character, and be 5-20 characters long")
    private String managerPassword;
}

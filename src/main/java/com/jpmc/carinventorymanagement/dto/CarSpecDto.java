package com.jpmc.carinventorymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarSpecDto {
    @NotBlank(message = "CarMake is mandatory")
    private String carMake;
    @NotBlank(message = "carModel is required")
    private String carModel;
    @NotBlank(message = "Fuel type is required")
    private String fuelType;
    private String color;
}

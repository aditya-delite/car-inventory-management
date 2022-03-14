package com.jpmc.carinventorymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {
    private String customerId;
    private String name;
    private String identifierId;
}

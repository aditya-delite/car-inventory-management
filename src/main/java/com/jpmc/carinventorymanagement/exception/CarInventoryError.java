package com.jpmc.carinventorymanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@Data
@AllArgsConstructor
public class CarInventoryError {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
}

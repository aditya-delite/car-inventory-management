package com.jpmc.carinventorymanagement.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Buyer {

    private final Integer buyerId;
    private final String name;
    private final String identifierId;

    private List<Car> cars;
}

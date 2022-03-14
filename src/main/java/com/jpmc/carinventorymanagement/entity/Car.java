package com.jpmc.carinventorymanagement.entity;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String carId;
    private String carMake;
    private String carModel;
    private String fuelType;
    private String color;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carMake.equals(car.carMake) && carModel.equals(car.carModel) && fuelType.equals(car.fuelType) && color.equals(car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carMake, carModel, fuelType, color);
    }
}

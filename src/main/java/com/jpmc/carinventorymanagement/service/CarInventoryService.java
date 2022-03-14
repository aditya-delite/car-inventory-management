package com.jpmc.carinventorymanagement.service;

import com.jpmc.carinventorymanagement.dto.CarSpecDto;
import com.jpmc.carinventorymanagement.entity.Car;

import java.util.List;

public interface CarInventoryService {

    Car addCarToInventory(CarSpecDto carSpec);

    Car removeCarFromInventory(CarSpecDto carSpec);

    Car purchaseCar(CarSpecDto carSpec, int buyerId);

    List<Car> fetchAvailableCars();
}

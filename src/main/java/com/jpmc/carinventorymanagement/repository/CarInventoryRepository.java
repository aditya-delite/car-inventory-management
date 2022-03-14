package com.jpmc.carinventorymanagement.repository;

import com.jpmc.carinventorymanagement.entity.Car;
import com.jpmc.carinventorymanagement.enums.CarMakeEnum;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public interface CarInventoryRepository {
    Car addCarToInventory(Car car);

    Car removeCarFromInventory(Car car);

    Car purchaseCar(Car car);

    List<Car> fetchAvailableCars();

    CopyOnWriteArrayList<Car> getAllCarsByCarMake(CarMakeEnum carMakeEnum);
}

package com.jpmc.carinventorymanagement.service;

import com.jpmc.carinventorymanagement.dto.CarSpecDto;
import com.jpmc.carinventorymanagement.entity.Car;
import com.jpmc.carinventorymanagement.enums.CarMakeEnum;
import com.jpmc.carinventorymanagement.exception.CarNotFoundException;
import com.jpmc.carinventorymanagement.repository.BuyerRepository;
import com.jpmc.carinventorymanagement.repository.CarInventoryRepository;
import com.jpmc.carinventorymanagement.repository.InventoryHelper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.jpmc.carinventorymanagement.constants.CarInventoryConstants.CAR_NOT_FOUND;
import static com.jpmc.carinventorymanagement.constants.CarInventoryConstants.NO_CAR_MATCH;

@Service
@Slf4j
public class CarInventoryServiceImpl implements CarInventoryService {

    @Autowired
    private CarInventoryRepository carInventoryRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public Car addCarToInventory(CarSpecDto carSpec) {

        Car car = mapper.map(carSpec, Car.class);
        car.setCarId(UUID.randomUUID().toString());
        log.info("Calling carInventory repository to add car for Car :" + car);
        return carInventoryRepository.addCarToInventory(car);
    }

    @Override
    public Car removeCarFromInventory(CarSpecDto carSpec) {
        Car car = mapper.map(carSpec, Car.class);
        return carInventoryRepository.removeCarFromInventory(car);
    }

    @Override
    public Car purchaseCar(CarSpecDto carSpec, int buyerId) {
        Car car = mapper.map(carSpec, Car.class);
        Car validCar = validateCar(car);
        buyerRepository.updateBuyer(buyerId, validCar);
        return carInventoryRepository.purchaseCar(validCar);
    }

    private Car validateCar(Car car) {
        CarMakeEnum carMakeEnum = InventoryHelper.getCarMakeEnum(car);
        CopyOnWriteArrayList<Car> cars = carInventoryRepository.getAllCarsByCarMake(carMakeEnum);
        return Optional.ofNullable(cars).stream().flatMap(Collection::stream)
                .filter(availableCar -> availableCar.getCarModel().equalsIgnoreCase(car.getCarModel())
                        && availableCar.getFuelType().equalsIgnoreCase(car.getFuelType())
                        && availableCar.getColor().equalsIgnoreCase(car.getColor()))
                .findFirst().orElseThrow(() -> new CarNotFoundException(CAR_NOT_FOUND, NO_CAR_MATCH));
    }

    @Override
    public List<Car> fetchAvailableCars() {
        return carInventoryRepository.fetchAvailableCars();
    }
}

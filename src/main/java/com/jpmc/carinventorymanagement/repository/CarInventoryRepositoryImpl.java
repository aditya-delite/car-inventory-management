package com.jpmc.carinventorymanagement.repository;

import com.jpmc.carinventorymanagement.entity.Car;
import com.jpmc.carinventorymanagement.enums.CarMakeEnum;
import com.jpmc.carinventorymanagement.exception.CarInventoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import static com.jpmc.carinventorymanagement.constants.CarInventoryConstants.*;

@Repository
@Slf4j
public class CarInventoryRepositoryImpl implements CarInventoryRepository {
    private static Map<CarMakeEnum, CopyOnWriteArrayList<Car>> availableCarsRepo;

    static {
        availableCarsRepo = InventoryHelper.getAvailableCarsData();
    }

    @Override
    public Car addCarToInventory(Car car) {
        CarMakeEnum carMakeEnum = InventoryHelper.getCarMakeEnum(car);
        if (carMakeEnum == null) {
            log.error("Exception occurred for carMake is null");
            throw new CarInventoryException(CAR_NOT_ADDED, NO_CAR_ADDED);
        }
        CopyOnWriteArrayList<Car> availableCars = availableCarsRepo.get(carMakeEnum);
        availableCars.add(car);
        availableCarsRepo.put(carMakeEnum, availableCars);
        log.info("successfully added car: {} in inventory", car);
        return car;
    }

    @Override
    public Car removeCarFromInventory(Car car) {
        CarMakeEnum carMakeEnum = InventoryHelper.getCarMakeEnum(car);
        if (Objects.isNull(carMakeEnum)) {
            log.error("cannot remove from inventory as carMake is null");
            throw new CarInventoryException(CAR_NOT_REMOVED, NO_CAR_REMOVED);
        }
        CopyOnWriteArrayList<Car> allCarsByCarMake = getAllCarsByCarMake(carMakeEnum);
        allCarsByCarMake.stream().filter(available -> available.equals(car))
                .limit(1)
                .forEach(allCarsByCarMake::remove);
        availableCarsRepo.put(carMakeEnum, allCarsByCarMake);
        return car;
    }


    @Override
    public Car purchaseCar(Car car) {
        CarMakeEnum carMakeEnum = InventoryHelper.getCarMakeEnum(car);
        CopyOnWriteArrayList<Car> cars = getAllCarsByCarMake(carMakeEnum);
        cars.stream().filter(availableCar -> availableCar.equals(car))
                .forEach(cars::remove);
        availableCarsRepo.put(carMakeEnum, cars);
        return car;
    }

    @Override
    public List<Car> fetchAvailableCars() {
        log.info("Returning available cars from the inventory");
        return availableCarsRepo.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()).collect(Collectors.toList());
    }

    public CopyOnWriteArrayList<Car> getAllCarsByCarMake(CarMakeEnum carMakeEnum) {
        return availableCarsRepo.get(carMakeEnum);
    }
}

package com.jpmc.carinventorymanagement.repository;

import com.jpmc.carinventorymanagement.entity.Buyer;
import com.jpmc.carinventorymanagement.enums.CarColorsEnum;
import com.jpmc.carinventorymanagement.enums.CarMakeEnum;
import com.jpmc.carinventorymanagement.entity.Car;
import com.jpmc.carinventorymanagement.entity.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.jpmc.carinventorymanagement.constants.CarInventoryConstants.FUEL_DIESEL;
import static com.jpmc.carinventorymanagement.constants.CarInventoryConstants.FUEL_PETROL;

public class InventoryHelper {

    public static Map<CarMakeEnum, CopyOnWriteArrayList<Car>> getAvailableCarsData() {
        Map<CarMakeEnum, CopyOnWriteArrayList<Car>> availableCarsRepo = new HashMap<>();
        List<Car> kiaCars = Arrays.asList(new Car("K1", CarMakeEnum.KIA.getCarMake(), "Celtos", FUEL_PETROL, CarColorsEnum.WHITE.getColor()),
                new Car("K2", CarMakeEnum.KIA.getCarMake(), "Carens", FUEL_PETROL, CarColorsEnum.BLACK.getColor()),
                new Car("K3", CarMakeEnum.KIA.getCarMake(), "Celtos", FUEL_PETROL, CarColorsEnum.WHITE.getColor()));
        List<Car> hondaCars = Arrays.asList(new Car("HON1", CarMakeEnum.HONDA.getCarMake(), "City", FUEL_DIESEL, CarColorsEnum.GREY.getColor()),
                new Car("HON2", CarMakeEnum.HONDA.getCarMake(), "CRV", FUEL_PETROL, CarColorsEnum.BLUE.getColor()),
                new Car("HON3", CarMakeEnum.HONDA.getCarMake(), "City", FUEL_PETROL, CarColorsEnum.WHITE.getColor()));
        List<Car> hyndaiCars = Arrays.asList(new Car("HYN1", CarMakeEnum.HYUNDAI.getCarMake(), "Creta", FUEL_DIESEL, CarColorsEnum.GREY.getColor()),
                new Car("HYN2", CarMakeEnum.HYUNDAI.getCarMake(), "Grand i10", FUEL_PETROL, CarColorsEnum.BLUE.getColor()),
                new Car("HYN3", CarMakeEnum.HYUNDAI.getCarMake(), "Elantra", FUEL_PETROL, CarColorsEnum.WHITE.getColor()));
        List<Car> fordCars = Arrays.asList(new Car("F1", CarMakeEnum.FORD.getCarMake(), "EcoSport", FUEL_DIESEL, CarColorsEnum.ORANGE.getColor()),
                new Car("F2", CarMakeEnum.FORD.getCarMake(), "Endeavour", FUEL_PETROL, CarColorsEnum.BLUE.getColor()),
                new Car("F3", CarMakeEnum.FORD.getCarMake(), "Figo", FUEL_PETROL, CarColorsEnum.WHITE.getColor()));

        availableCarsRepo.put(CarMakeEnum.KIA, new CopyOnWriteArrayList<>(kiaCars));
        availableCarsRepo.put(CarMakeEnum.HONDA, new CopyOnWriteArrayList<>(hondaCars));
        availableCarsRepo.put(CarMakeEnum.HYUNDAI, new CopyOnWriteArrayList<>(hyndaiCars));
        availableCarsRepo.put(CarMakeEnum.FORD, new CopyOnWriteArrayList<>(fordCars));
        return availableCarsRepo;
    }

    public static Map<Integer, User> getUsers() {
        Map<Integer, User> users = new HashMap<>();
        users.put(100, new User(100, "jpmcAdmin0", true));
        users.put(101, new User(101, "jpmcAdmin1", true));
        users.put(102, new User(102, "jpmcAdmin2", true));
        users.put(103, new User(103, "jpmcAdmin3", true));
        users.put(104, new User(104, "jpmcAdmin4", true));
        users.put(105, new User(105, "jpmcAdmin5", false));
        users.put(106, new User(106, "jpmcAdmin6", false));
        users.put(107, new User(107, "jpmcAdmin7", false));
        users.put(108, new User(108, "jpmcAdmin8", false));
        return users;
    }

    public static Map<Integer, Buyer> getBuyers() {
        Map<Integer, Buyer> buyers = new HashMap<>();
        buyers.put(300, new Buyer(300, "jpmcBuyer0", "id0"));
        buyers.put(301, new Buyer(301, "jpmcBuyer1", "id1"));
        buyers.put(302, new Buyer(302, "jpmcBuyer2", "id2"));
        buyers.put(303, new Buyer(303, "jpmcBuyer3", "id3"));
        buyers.put(304, new Buyer(304, "jpmcBuyer4", "id4"));
        buyers.put(305, new Buyer(305, "jpmcBuyer5", "id5"));
        buyers.put(306, new Buyer(306, "jpmcBuyer6", "id6"));
        buyers.put(307, new Buyer(307, "jpmcBuyer7", "id7"));
        buyers.put(308, new Buyer(308, "jpmcBuyer8", "id8"));
        return buyers;
    }

    public static CarMakeEnum getCarMakeEnum(Car car) {
        return CarMakeEnum.getCarMakeEnum(car.getCarMake());
    }
}

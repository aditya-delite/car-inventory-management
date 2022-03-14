package com.jpmc.carinventorymanagement.controller;

import com.jpmc.carinventorymanagement.dto.CarSpecDto;
import com.jpmc.carinventorymanagement.entity.Car;
import com.jpmc.carinventorymanagement.exception.AdminAccessException;
import com.jpmc.carinventorymanagement.repository.BuyerRepository;
import com.jpmc.carinventorymanagement.repository.UserRepository;
import com.jpmc.carinventorymanagement.service.CarInventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.jpmc.carinventorymanagement.constants.CarInventoryConstants.*;

@RestController
@RequestMapping("/inventory")
@Slf4j
public class CarInventoryController {

    @Autowired
    private CarInventoryService carInventoryService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @GetMapping("/buyer/cars")
    public ResponseEntity<List<Car>> getAvailableCars() {
        log.info("Fetching available cars");
        List<Car> carList = carInventoryService.fetchAvailableCars();
        return ResponseEntity.ok(carList);
    }

    @PostMapping("/buyer/{buyerId}")
    public ResponseEntity<Car> purchaseCar(@Valid @RequestBody CarSpecDto carSpecDto,
                                           @PathVariable("buyerId") Integer buyerId) {
        boolean isBuyer = buyerRepository.isBuyer(buyerId);
        if (!isBuyer) {
            throw new AdminAccessException("Not authorized", BUYER_CAR_PURCHASE_ERROR_MSG);
        }
        Car purchasedCar = carInventoryService.purchaseCar(carSpecDto, buyerId);
        return ResponseEntity.status(HttpStatus.OK).body(purchasedCar);
    }

    @PostMapping("/users/{userId}/car")
    public ResponseEntity<Car> addCarToInventory(@PathVariable("userId") Integer userId,
                                               @Valid @RequestBody CarSpecDto car) {
        boolean isAdmin = userRepository.isAdminUser(userId);
        if (!isAdmin) {
            throw new AdminAccessException("Not authorized", ADMIN_CAR_NOT_ADDED_MSG);
        }
        Car addedCar = carInventoryService.addCarToInventory(car);
        return ResponseEntity.status(HttpStatus.OK).body(addedCar);
    }

    @DeleteMapping("/users/{userId}/car")
    public ResponseEntity<?> removeCarFromInventory(@PathVariable("userId") Integer userId,
                                                    @Valid @RequestBody CarSpecDto car) {
        boolean isAdmin = userRepository.isAdminUser(userId);
        if (!isAdmin) {
            throw new AdminAccessException("Not authorized", ADMIN_CAR_NOT_REMOVED_MSG);
        }
        Car removedCar = carInventoryService.removeCarFromInventory(car);
        return ResponseEntity.status(HttpStatus.OK).body(removedCar);
    }
}

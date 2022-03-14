package com.jpmc.carinventorymanagement.repository;

import com.jpmc.carinventorymanagement.entity.Buyer;
import com.jpmc.carinventorymanagement.entity.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class BuyerRepositoryImpl implements BuyerRepository {

    private static Map<Integer, Buyer> buyersMap = new HashMap<>();

    static {
        buyersMap = InventoryHelper.getBuyers();
    }

    @Override
    public boolean isBuyer(int userId) {
        return buyersMap.containsKey(userId);
    }

    @Override
    public void updateBuyer(int buyerId, Car car) {
        Buyer buyer = buyersMap.get(buyerId);
        log.info("Existing customer: {}", buyer.getBuyerId());
        List<Car> carsWithCustomer = buyer.getCars();
        if(Objects.isNull(carsWithCustomer))
            carsWithCustomer = new ArrayList<>();
        carsWithCustomer.add(car);
        buyer.setCars(carsWithCustomer);
        buyersMap.put(buyerId, buyer);
        log.info("Successfully updated the buyerId {} with new car details {}", buyerId, car);
    }
}

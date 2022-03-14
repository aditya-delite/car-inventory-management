package com.jpmc.carinventorymanagement.repository;

import com.jpmc.carinventorymanagement.entity.Buyer;
import com.jpmc.carinventorymanagement.entity.Car;

public interface BuyerRepository {

    boolean isBuyer(int buyerId);

    void updateBuyer(int buyerId, Car car);
}

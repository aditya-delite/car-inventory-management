package com.jpmc.carinventorymanagement.repository;

import com.jpmc.carinventorymanagement.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static Map<Integer, User> usersMap = new HashMap<>();

    static {
        usersMap = InventoryHelper.getUsers();
    }

    @Override
    public boolean isAdminUser(int userId) {
        if (usersMap.containsKey(userId)) {
            User user = usersMap.get(userId);
            return user.isAdmin();
        }
        return false;
    }
}

package com.example.ws.dao.impl;

import com.example.ws.dao.UserRepository;
import com.example.ws.utils.FavorOrderBuilder;
import io.spring.guides.gs_producing_web_service.Status;
import io.spring.guides.gs_producing_web_service.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<BigInteger, User> userMap = new HashMap<>();

    @PostConstruct
    public void populateUsers() {
        var mickael = new User();

        BigInteger mickaelId = BigInteger.ONE;

        mickael.setId(mickaelId);
        mickael.setName("Mickael");
        mickael.getOrders().add(
                new FavorOrderBuilder()
                        .withId(BigInteger.ONE)
                        .withFavorId(BigInteger.ONE)
                        .withOrderedById(mickaelId)
                        .withStatus(Status.CREATED)
                        .build()
        );

        var jess = new User();

        BigInteger jessId = BigInteger.TWO;

        jess.setId(jessId);
        jess.setName("Jess");
        jess.getOrders().add(
                new FavorOrderBuilder()
                        .withId(BigInteger.TWO)
                        .withFavorId(BigInteger.ONE)
                        .withOrderedById(jessId)
                        .withStatus(Status.IN_PROGRESS)
                        .build()
        );

        userMap.put(mickaelId, mickael);
        userMap.put(jessId, jess);
    }

    @Override
    public Collection<User> getAllUsers() {
        return userMap.values();
    }

    @Override
    public User getUserById(BigInteger id) {
        return userMap.get(id);
    }

}

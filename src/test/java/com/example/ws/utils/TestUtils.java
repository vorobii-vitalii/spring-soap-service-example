package com.example.ws.utils;

import io.spring.guides.gs_producing_web_service.Favor;
import io.spring.guides.gs_producing_web_service.Status;
import io.spring.guides.gs_producing_web_service.User;

import java.math.BigInteger;

public class TestUtils {

    public static User getExpectedUser() {
        var user = new User();

        user.setName("George");
        user.setId(BigInteger.ONE);
        user.getOrders().add(
                new FavorOrderBuilder()
                    .withId(BigInteger.ONE)
                    .withFavorId(BigInteger.ONE)
                    .withOrderedById(BigInteger.ONE)
                    .withStatus(Status.CREATED)
                .build()
        );

        return user;
    }

    public static Favor getExpectedFavor() {
        var favor = new Favor();

        favor.setId(BigInteger.ONE);
        favor.setDescription("Description");

        return favor;
    }

}

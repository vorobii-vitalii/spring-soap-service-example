package com.example.ws.dao;

import io.spring.guides.gs_producing_web_service.User;

import java.math.BigInteger;
import java.util.Collection;

public interface UserRepository {
    Collection<User> getAllUsers();
    User getUserById(BigInteger id);
}

package com.example.ws.dao.impl;

import com.example.ws.dao.FavorRepository;
import io.spring.guides.gs_producing_web_service.Favor;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FavorRepositoryImpl implements FavorRepository {
    private List<Favor> favors = new ArrayList<>();

    @PostConstruct
    public void populateFavors() {
        var favor = new Favor();

        favor.setId(BigInteger.ONE);
        favor.setDescription("Example description");

        favors.add(favor);
    }

    @Override
    public List<Favor> getAllFavors() {
        return favors;
    }

}

package com.example.ws.dao;

import io.spring.guides.gs_producing_web_service.Favor;

import java.util.List;

public interface FavorRepository {
    List<Favor> getAllFavors();
}

package com.example.ws.utils;

import io.spring.guides.gs_producing_web_service.FavorOrder;
import io.spring.guides.gs_producing_web_service.Status;

import java.math.BigInteger;

public class FavorOrderBuilder {
    private BigInteger id;
    private BigInteger orderedById;
    private BigInteger favorId;
    private Status status;

    public FavorOrderBuilder withId(BigInteger id) {
        this.id = id;

        return this;
    }

    public FavorOrderBuilder withOrderedById(BigInteger orderedById) {
        this.orderedById = orderedById;

        return this;
    }

    public FavorOrderBuilder withFavorId(BigInteger favorId) {
        this.favorId = favorId;

        return this;
    }

    public FavorOrderBuilder withStatus(Status status) {
        this.status = status;

        return this;
    }

    public FavorOrder build() {
        final FavorOrder favorOrder = new FavorOrder();

        favorOrder.setId(id);
        favorOrder.setFavorId(favorId);
        favorOrder.setOrderedById(orderedById);
        favorOrder.setStatus(status);

        return favorOrder;
    }

}

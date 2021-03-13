package com.example.ws.controller;

import com.example.ws.dao.FavorRepository;
import com.example.ws.utils.WebServiceConstants;
import io.spring.guides.gs_producing_web_service.GetFavorsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class FavorEndpoint {
    private final FavorRepository favorRepository;

    @PayloadRoot(namespace = WebServiceConstants.NAMESPACE_URL, localPart = "getAllFavorsRequest")
    @ResponsePayload
    public GetFavorsResponse getAllFavors() {
        final GetFavorsResponse getFavorsResponse = new GetFavorsResponse();

        var favors = favorRepository.getAllFavors();

        getFavorsResponse.getFavors().addAll(favors);

        return getFavorsResponse;
    }


}

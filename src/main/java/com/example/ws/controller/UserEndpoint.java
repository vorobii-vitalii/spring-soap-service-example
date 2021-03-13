package com.example.ws.controller;

import com.example.ws.dao.UserRepository;
import com.example.ws.utils.WebServiceConstants;
import io.spring.guides.gs_producing_web_service.GetAllUsersResponse;
import io.spring.guides.gs_producing_web_service.GetUserByIdRequest;
import io.spring.guides.gs_producing_web_service.GetUserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class UserEndpoint {
    private final UserRepository userRepository;

    @PayloadRoot(namespace = WebServiceConstants.NAMESPACE_URL, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserResponse getUserById(@RequestPayload GetUserByIdRequest getUserByIdRequest) {
        final GetUserResponse getUserResponse = new GetUserResponse();

        var user = userRepository.getUserById(getUserByIdRequest.getId());

        getUserResponse.setUser(user);

        return getUserResponse;
    }

    @PayloadRoot(namespace = WebServiceConstants.NAMESPACE_URL, localPart = "getAllUsersRequest")
    @ResponsePayload
    public GetAllUsersResponse getAllUsers() {
        final GetAllUsersResponse getAllUsersResponse = new GetAllUsersResponse();

        var users = userRepository.getAllUsers();

        getAllUsersResponse.getUsers().addAll(users);

        return getAllUsersResponse;
    }

}

package org.ecommerce.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.*;
import org.ecommerce.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.sql.Timestamp;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;

    private String version = "v0.1";

    @GetMapping(
            path = "/api/ecommerce/v0.1/user",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<UserResponse> get(User user) {
        UserResponse response = userService.get(user);

        return DataResponse.<UserResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success get user")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(this.request.getServletPath())
                .build();
    }

    @PutMapping(
            path = "/api/ecommerce/v0.1/user/name",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<NameUserResponse> changeName(@RequestBody NameUserRequest request, User user) {
        NameUserResponse response = userService.changeEmail(user, request);

        return DataResponse.<NameUserResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success change your name")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(this.request.getServletPath())
                .build();
    }

    @PostMapping(
            path = "/api/ecommerce/v0.1/user/birth",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE

    )
    public DataResponse<BirthUserResponse> addBirth(@RequestBody BirthUserRequest request, User user){
        BirthUserResponse response = userService.addBirth(user, request);

        return DataResponse.<BirthUserResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success add your birth")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(this.request.getServletPath())
                .build();
    }
}














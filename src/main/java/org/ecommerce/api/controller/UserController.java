package org.ecommerce.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.ecommerce.api.model.DataResponse;
import org.ecommerce.api.model.EmailRequest;
import org.ecommerce.api.model.ValidateEmailResponse;
import org.ecommerce.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest servletRequest;

    @PostMapping(
            path = "/api/ecommerce/v0.1/auth/validate_email/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<ValidateEmailResponse> validateEmail(@PathVariable("email") EmailRequest email) {
        ValidateEmailResponse response = userService.validateEmail(email);

        return DataResponse.<ValidateEmailResponse>builder()
                .api_version("v0.1")
                .status_code(HttpStatus.OK.value())
                .message("Success send otp to email, check your email")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(servletRequest.getServletPath())
                .build();
    }
}

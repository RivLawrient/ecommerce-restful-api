package org.ecommerce.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.*;
import org.ecommerce.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.sql.Time;
import java.sql.Timestamp;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private HttpServletRequest servletRequest;

    private String version= "v0.1";

    @PostMapping(
            path = "/api/ecommerce/v0.1/auth/validate_email/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<ValidateEmailResponse> validateEmail(@PathVariable("email") EmailRequest email) {
        ValidateEmailResponse response = authService.validateEmail(email);

        return DataResponse.<ValidateEmailResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success send otp to email, check your email")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(servletRequest.getServletPath())
                .build();
    }

    @PostMapping(
            path = "/api/ecommerce/v0.1/auth/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<RegisterResponse> response(@RequestBody RegisterRequest request){
        RegisterResponse response = authService.register(request);

        return DataResponse.<RegisterResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success register your email")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(servletRequest.getServletPath())
                .build();
    }

    @GetMapping(
            path = "/api/ecommerce/v0.1/auth/otp_login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<OtpLoginResponse> otpLogin(@RequestBody OtpLoginRequest request){
        OtpLoginResponse response = authService.otpLogin(request);

        return DataResponse.<OtpLoginResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success get otp for your account, check your email")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(servletRequest.getServletPath())
                .build();
    }

    @GetMapping(
            path = "/api/ecommerce/v0.1/auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response = authService.login(request);

        return DataResponse.<LoginResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success to login")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(servletRequest.getServletPath())
                .build();
    }

    @DeleteMapping(
            path = "/api/ecommerce/v0.1/auth/logout",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<LogoutResponse> logout(User user){
        LogoutResponse response = authService.logout(user);

        return DataResponse.<LogoutResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success to logout")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(servletRequest.getServletPath())
                .build();
    }
}

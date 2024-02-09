package org.ecommerce.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.DataResponse;
import org.ecommerce.api.model.SellerRegisterRequest;
import org.ecommerce.api.model.SellerRegisterResponse;
import org.ecommerce.api.service.SellerService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.Timestamp;

@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private HttpServletRequest request;

    private String version = "v0.1";

    @PostMapping(
            path = "/api/ecommerce/v0.1/user/seller",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<SellerRegisterResponse> register (@RequestBody SellerRegisterRequest request, User user){
        SellerRegisterResponse response = sellerService.register(user, request);

        return DataResponse.<SellerRegisterResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success register for seller")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(this.request.getServletPath())
                .build();
    }
}

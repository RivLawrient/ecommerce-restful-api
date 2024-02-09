package org.ecommerce.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.AddressRequest;
import org.ecommerce.api.model.AddressResponse;
import org.ecommerce.api.model.DataResponse;
import org.ecommerce.api.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private HttpServletRequest request;

    private String version = "v0.1";

    @PostMapping(
            path = "/api/ecommerce/v0.1/user/address",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<AddressResponse> add(@RequestBody AddressRequest request, User user){
        AddressResponse response = addressService.add(user, request);

        return DataResponse.<AddressResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success add new address")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(this.request.getServletPath())
                .build();
    }

    @PutMapping(
            path = "/api/ecommerce/v0.1/user/address/{addressId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<AddressResponse> edit(@PathVariable("addressId") String addressId,@RequestBody AddressRequest request, User user){
        AddressResponse response = addressService.edit(user, request, addressId);

        return DataResponse.<AddressResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success edit a address")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(this.request.getServletPath())
                .build();
    }

    @PutMapping(
            path = "/api/ecommerce/v0.1/user/main_address/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<AddressResponse> main(@PathVariable("addressId") String addressId, User user){
        AddressResponse response = addressService.main(user, addressId);

        return DataResponse.<AddressResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success select main address")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(this.request.getServletPath())
                .build();
    }

    @DeleteMapping(
            path = "/api/ecommerce/v0.1/user/address/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DataResponse<AddressResponse> delete(@PathVariable("addressId") String addressId, User user){
        AddressResponse response = addressService.remove(user, addressId);

        return DataResponse.<AddressResponse>builder()
                .api_version(version)
                .status_code(HttpStatus.OK.value())
                .message("Success remove address")
                .data(response)
                .timestamp(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .path(this.request.getServletPath())
                .build();
    }
}

package org.ecommerce.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.api.entity.Seller;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.DataResponse;
import org.ecommerce.api.model.ErrorResponse;
import org.ecommerce.api.model.SellerRegisterRequest;
import org.ecommerce.api.model.SellerRegisterResponse;
import org.ecommerce.api.repository.AddressRepository;
import org.ecommerce.api.repository.SellerRepository;
import org.ecommerce.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;


    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        sellerRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setId("user1");
        user.setName("MuhSans");
        user.setEmail("san@gmail.com");
        user.setToken("token");
        user.setTokenExpiredAt(100000000000000000L);
        user.setCreatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
        userRepository.save(user);
    }

    @Test
    void registerSellerSuccess() throws Exception{
        SellerRegisterRequest request = new SellerRegisterRequest();
        request.setName("SanSan");
        request.setDomain("ikoku");
        request.setProvince("kaltim");
        request.setCity("sangt");
        request.setPostalCode("1212");

        mockMvc.perform(
                post("/api/ecommerce/v0.1/user/seller")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("User-Token", "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<SellerRegisterResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<SellerRegisterResponse>>() {
            });

            assertEquals(response.getMessage(), "Success register for seller");
            assertEquals(response.getData().getName(), request.getName());
        });
    }

    @Test
    void registerSellerFailed() throws Exception{
        User user = userRepository.findById("user1").orElseThrow();

        Seller seller = new Seller();
        seller.setId(UUID.randomUUID().toString());
        seller.setUser(user);
        sellerRepository.save(seller);

        SellerRegisterRequest request = new SellerRegisterRequest();
        request.setName("SanSan");
        request.setDomain("ikoku");
        request.setProvince("kaltim");
        request.setCity("sangt");
        request.setPostalCode("1212");

        mockMvc.perform(
                post("/api/ecommerce/v0.1/user/seller")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("User-Token", "token")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            DataResponse<ErrorResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ErrorResponse>>() {
            });

            assertEquals(response.getMessage(), "Failed, something wrong");
            assertEquals(response.getData().getError(), "your user was a seller");
        });
    }
}
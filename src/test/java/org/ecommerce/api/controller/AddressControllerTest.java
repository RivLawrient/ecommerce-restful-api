package org.ecommerce.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.AddressRequest;
import org.ecommerce.api.model.AddressResponse;
import org.ecommerce.api.model.DataResponse;
import org.ecommerce.api.model.ErrorResponse;
import org.ecommerce.api.repository.AddressRepository;
import org.ecommerce.api.repository.UserRepository;
import org.ecommerce.api.security.BCrypt;
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
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;


    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setPassword(BCrypt.hashpw("121", BCrypt.gensalt()));
        user.setToken("okegas");
        user.setTokenExpiredAt(10000000000000000L);
        user.setCreatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
        userRepository.save(user);
    }

    @Test
    void addAddressSuccess() throws Exception{
        AddressRequest request = new AddressRequest();
        request.setName("MSANDI");
        request.setHandphone("0808080");
        request.setLabel("apartment");
        request.setCity("Sangatta Utara");
        request.setProvince("Kal-Tim");
        request.setPostalCode("12123");
        request.setCompleteAddress("pokoknya lengkap pake nomer dan jalan");
        request.setCoordinate("1,123123,123");
        request.setNote("warna merah");
        request.setMainAddress(true);

        mockMvc.perform(
                post("/api/ecommerce/v0.1/user/address")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("User-Token", "okegas")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<AddressResponse>>() {
            });

            assertEquals(response.getMessage(), "Success add new address");
            assertEquals(response.getData().getName(), request.getName());
        });
    }

    @Test
    void addAddressFailed() throws Exception{
        AddressRequest request = new AddressRequest();
        request.setName("MSANDI");
        request.setHandphone("0808080");
        request.setLabel("apartment");
        request.setCity("Sangatta Utara");
        request.setProvince("Kal-Tim");
        request.setPostalCode("12123");
        request.setCompleteAddress("pokoknya lengkap pake nomer dan jalan");
        request.setCoordinate("1,123123,123");
        request.setNote("warna merah");
        request.setMainAddress(true);

        mockMvc.perform(
                post("/api/ecommerce/v0.1/user/address")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("User-Token", "okegass")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            DataResponse<ErrorResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ErrorResponse>>() {
            });

            assertEquals(response.getMessage(), "Failed, something wrong");
            assertEquals(response.getData().getError(), "your token is wrong");
        });
    }
}
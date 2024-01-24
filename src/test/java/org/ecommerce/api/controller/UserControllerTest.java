package org.ecommerce.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.*;
import org.ecommerce.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.crypto.Data;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void validateEmailSuccess() throws Exception {
        ValidateEmailResponse email = new ValidateEmailResponse();
        email.setEmail("blabla@gmail.com");

        mockMvc.perform(
                post("/api/ecommerce/v0.1/auth/validate_email/" + email.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(email))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<ValidateEmailResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ValidateEmailResponse>>() {
            });

            assertEquals(response.getMessage(), "Success send otp to email, check your email");
            assertEquals(response.getData().getEmail(), email.getEmail());
        });
    }

    @Test
    void validateEmailFailed() throws Exception {
        User user = new User();
        user.setId("randomString");
        user.setName("adadeh");
        user.setEmail("balbal@gmail.con");
        userRepository.save(user);

        mockMvc.perform(
                post("/api/ecommerce/v0.1/auth/validate_email/" + user.getEmail())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            DataResponse<ErrorResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ErrorResponse>>() {
            });

            assertEquals(response.getMessage(), "Failed, something wrong");
            assertEquals(response.getData().getError(), "email has been previously registered, please log in");
        });
    }

    //------------------------------------------------------REGISTER-----------------------------------------


    @Test
    void registerSuccess() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("1@tes.com");
        user.setEmailOtp("123123");

        userRepository.save(user);

        RegisterRequest request = new RegisterRequest();
        request.setEmail("1@tes.com");
        request.setOtp("123123");
        request.setName("Muh Sandi");
        request.setPassword("secret");

        mockMvc.perform(
                post("/api/ecommerce/v0.1/auth/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<RegisterResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<RegisterResponse>>() {
            });

            assertEquals(response.getMessage(), "Success register your email");
            assertEquals(response.getData().getEmail(), user.getEmail());
        });
    }

    @Test
    void registerFailed() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail("1@tes.com");
        user.setEmailOtp("123123");

        userRepository.save(user);

        RegisterRequest request = new RegisterRequest();
        request.setEmail("1@tes.com");
        request.setOtp("salah");
        request.setName("Muh Sandi");
        request.setPassword("secret");

        mockMvc.perform(
                post("/api/ecommerce/v0.1/auth/register")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            DataResponse<ErrorResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ErrorResponse>>() {
            });

            assertEquals(response.getMessage(), "Failed, something wrong");
            assertEquals(response.getData().getError(), "otp email is wrong, check your email");
        });
    }
}
package org.ecommerce.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.*;
import org.ecommerce.api.repository.AddressRepository;
import org.ecommerce.api.repository.UserRepository;
import org.ecommerce.api.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

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

    //---------------------------------------------------------OTP LOGIN-----------------------------------------------------

    @Test
    void otpLoginSuccess() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("1@tes.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));

        userRepository.save(user);

        RegisterRequest request = new RegisterRequest();
        request.setEmail("1@tes.com");
        request.setPassword("secret");

        mockMvc.perform(
                get("/api/ecommerce/v0.1/auth/otp_login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<OtpLoginResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<OtpLoginResponse>>() {
            });

            assertEquals(response.getMessage(), "Success get otp for your account, check your email");
            assertEquals(response.getData().getEmail(), user.getEmail());
        });
    }

    @Test
    void otpLoginFailed() throws Exception {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("1@tes.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));

        userRepository.save(user);

        RegisterRequest request = new RegisterRequest();
        request.setEmail("1@tes.com");
        request.setPassword("secrets");

        mockMvc.perform(
                get("/api/ecommerce/v0.1/auth/otp_login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            DataResponse<ErrorResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ErrorResponse>>() {
            });

            assertEquals(response.getMessage(), "Failed, something wrong");
            assertEquals(response.getData().getError(), "your email or password is wrong");
        });
    }

    //-------------------------------------------------------LOGIN---------------------------------------------------

    @Test
    void loginSuccess() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("1@tes.com");
        user.setEmailOtp("123123");
        user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));

        userRepository.save(user);

        LoginRequest request = new LoginRequest();
        request.setEmail("1@tes.com");
        request.setPassword("secrets");
        request.setOtp("123123");

        mockMvc.perform(
                get("/api/ecommerce/v0.1/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<LoginResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<LoginResponse>>() {
            });

            assertEquals(response.getMessage(), "Success to login");
            assertEquals(response.getData().getEmail(), request.getEmail());
        });
    }

    @Test
    void loginFailed() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("1@tes.com");
        user.setEmailOtp("123123");
        user.setPassword(BCrypt.hashpw("secrets", BCrypt.gensalt()));

        userRepository.save(user);

        LoginRequest request = new LoginRequest();
        request.setEmail("1@tes.com");
        request.setPassword("secrets");
        request.setOtp("1231");

        mockMvc.perform(
                get("/api/ecommerce/v0.1/auth/login")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            DataResponse<ErrorResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ErrorResponse>>() {
            });

            assertEquals(response.getMessage(), "Failed, something wrong");
            assertEquals(response.getData().getError(), "otp login is wrong, check again");
        });
    }

    //-------------------------------------------------------------LOGOUT-----------------------------------------------

    @Test
    void logoutSuccess() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setToken("ok");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000000000L);
        userRepository.save(user);


        mockMvc.perform(
                delete("/api/ecommerce/v0.1/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("User-Token", "ok")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<LogoutResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<LogoutResponse>>() {
            });

            assertEquals(response.getMessage(), "Success to logout");
            assertEquals(response.getData().getEmail(), user.getEmail());
        });
    }

    @Test
    void logoutFailed() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setToken("ok");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000000000L);
        userRepository.save(user);


        mockMvc.perform(
                delete("/api/ecommerce/v0.1/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("User-Token", "oks")
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
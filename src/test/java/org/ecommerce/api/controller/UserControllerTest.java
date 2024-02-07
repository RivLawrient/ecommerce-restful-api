package org.ecommerce.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.*;
import org.ecommerce.api.repository.UserRepository;
import org.ecommerce.api.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    void getUserSuccess() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setToken("ok");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000000000L);
        userRepository.save(user);

        mockMvc.perform(
                get("/api/ecommerce/v0.1/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("User-Token", "ok")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<UserResponse>>() {
            });

            assertEquals(response.getMessage(), "Success get user");
            assertEquals(response.getData().getEmail(), user.getEmail());
        });
    }

    @Test
    void getUserFailed() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setToken("ok");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000000000L);
        userRepository.save(user);

        mockMvc.perform(
                get("/api/ecommerce/v0.1/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("User-Token", "salah")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            DataResponse<ErrorResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ErrorResponse>>() {
            });

            assertEquals(response.getMessage(), "Failed, something wrong");
            assertEquals(response.getData().getError(), "your token is wrong");
        });
    }

    //---------------------------------------------------change name----------------------------------------------------

    @Test
    void changeNameSuccess() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setToken("ok");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000000000L);
        userRepository.save(user);

        NameUserRequest request = new NameUserRequest("Sansann");
        mockMvc.perform(
                put("/api/ecommerce/v0.1/user/name")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("User-Token", "ok")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<NameUserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<NameUserResponse>>() {
            });

            assertEquals(response.getMessage(), "Success change your name");
            assertEquals(response.getData().getEmail(), user.getEmail());
        });
    }

    @Test
    void changeNameFailed() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setToken("ok");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000000000L);
        userRepository.save(user);

        NameUserRequest request = new NameUserRequest("Sansann");
        mockMvc.perform(
                put("/api/ecommerce/v0.1/user/name")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
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

    //-----------------------------------------------------add birth----------------------------------------------------

    @Test
    void addBirthSuccess() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setToken("ok");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000000000L);
        userRepository.save(user);

        BirthUserRequest request = new BirthUserRequest();
        request.setDay(12);
        request.setMonth(2);
        request.setYear(2002);

        mockMvc.perform(
                post("/api/ecommerce/v0.1/user/birth")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("User-Token", "ok")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            DataResponse<BirthUserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<BirthUserResponse>>() {
            });

            assertEquals(response.getMessage(), "Success add your birth");
            assertEquals(response.getData().getEmail(), user.getEmail());
        });
    }
    @Test
    void addBirthFailed() throws Exception{
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("Muh Sandi");
        user.setEmail("san@gmail.com");
        user.setBirthDay("3924-01-12");
        user.setPassword(BCrypt.hashpw("secret", BCrypt.gensalt()));
        user.setToken("ok");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000000000L);
        userRepository.save(user);

        BirthUserRequest request = new BirthUserRequest();
        request.setDay(12);
        request.setMonth(2);
        request.setYear(2002);

        mockMvc.perform(
                post("/api/ecommerce/v0.1/user/birth")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("User-Token", "ok")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            DataResponse<ErrorResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<DataResponse<ErrorResponse>>() {
            });


            assertEquals(response.getMessage(), "Failed, something wrong");
            assertEquals(response.getData().getError(), "birth was add previously");
        });
    }
}
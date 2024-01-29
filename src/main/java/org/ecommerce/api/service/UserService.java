package org.ecommerce.api.service;

import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.*;
import org.ecommerce.api.repository.UserRepository;
import org.ecommerce.api.security.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ValidateService validateService;

    public UserResponse get(User user){
        return UserResponse.builder()
                .name(user.getName())
                .birthDay(user.getBirthDay())
                .gender(user.getGender())
                .profilePicture(user.getProfilePicture())
                .email(user.getEmail())
                .emailVerification(user.getEmailVerification())
                .updateAt(user.getUpdateAt())
                .createdAt(user.getCreatedAt())
                .build();
    }

    @Transactional
    public NameUserResponse changeEmail(User user, NameUserRequest request){
        validateService.validate(request);

        user.setName(request.getName());
        user.setUpdateAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
        userRepository.save(user);

        return NameUserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .updateAt(user.getUpdateAt())
                .build();
    }
}

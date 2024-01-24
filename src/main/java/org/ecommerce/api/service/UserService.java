package org.ecommerce.api.service;

import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.EmailRequest;
import org.ecommerce.api.model.RegisterRequest;
import org.ecommerce.api.model.RegisterResponse;
import org.ecommerce.api.model.ValidateEmailResponse;
import org.ecommerce.api.repository.UserRepository;
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

    @Transactional
    public ValidateEmailResponse validateEmail(EmailRequest email) {
        validateService.validate(email);

        if (userRepository.findUserByEmail(email.getEmail()).isEmpty()) {
            String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));

            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setEmail(email.getEmail());
            user.setEmailOtp(otp);
            userRepository.save(user);

//            emailService.sendEmail(email,"otp", "YOUR REGISTER OTP IS : " + otp );
            return ValidateEmailResponse.builder()
                    .email(email.getEmail())
                    .build();
        } else if (userRepository.findUserByEmail(email.getEmail()).get().getName() == null) {
            Optional<User> user = userRepository.findUserByEmail(email.getEmail());
            String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));

            user.get().setEmailOtp(otp);
            userRepository.save(user.get());

//            emailService.sendEmail(email,"otp", "YOUR REGISTER OTP IS : " + otp );
            return ValidateEmailResponse.builder()
                    .email(email.getEmail())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email has been previously registered, please log in");
        }
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        validateService.validate(request);

        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "email is not found"));

        if (user.getEmailOtp().equals(request.getOtp())) {
            if(user.getName() == null){
                user.setName(request.getName());
                user.setPassword(request.getPassword());
                user.setEmailVerification(true);
                user.setUpdateAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
                user.setCreatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())));

                userRepository.save(user);
            }else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email has been previously registered, please log in");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "otp email is wrong, check your email");
        }

        return RegisterResponse.builder()
                .email(request.getEmail())
                .name(request.getName())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

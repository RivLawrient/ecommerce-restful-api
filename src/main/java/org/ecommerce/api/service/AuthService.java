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
public class AuthService {

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
            if (user.getName() == null) {
                user.setName(request.getName());
                user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
                user.setEmailVerification(true);
                user.setEmailOtp(null);
                user.setToken(UUID.randomUUID().toString());
                user.setTokenExpiredAt(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7));
                user.setUpdateAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
                user.setCreatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
                userRepository.save(user);

                return RegisterResponse.builder()
                        .email(request.getEmail())
                        .name(request.getName())
                        .token(user.getToken())
                        .tokenExpiredAt(user.getTokenExpiredAt())
                        .updateAt(user.getUpdateAt())
                        .createdAt(user.getCreatedAt())
                        .build();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email has been previously registered, please log in");
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "otp email is wrong, check your email");
        }
    }

    @Transactional
    public OtpLoginResponse otpLogin(OtpLoginRequest request) {
        validateService.validate(request);

        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "email is not found"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
//            user.setToken(UUID.randomUUID().toString());
//            user.setTokenExpiredAt(System.currentTimeMillis() + (1000*60*60*24*7));
            String otp = new DecimalFormat("000000").format(new Random().nextInt(999999));

            user.setEmailOtp(otp);
            user.setUpdateAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
            userRepository.save(user);

//            emailService.sendEmail(user.getEmail(),"otp", "YOUR LOGIN OTP IS : " + otp );

            return OtpLoginResponse.builder()
                    .email(request.getEmail())
                    .name(user.getName())
                    .upadateAt(user.getUpdateAt())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "your email or password is wrong");
        }
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        validateService.validate(request);

        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "email is not found"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {

            if (user.getEmailOtp() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "account is already login");
            } else if (user.getEmailOtp().equals(request.getOtp())) {
                user.setEmailOtp(null);
                user.setToken(UUID.randomUUID().toString());
                user.setTokenExpiredAt(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7));
                user.setUpdateAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
                userRepository.save(user);

                return LoginResponse.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .token(user.getToken())
                        .tokenExpiredAt(user.getTokenExpiredAt())
                        .updateAt(user.getUpdateAt())
                        .build();
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "otp login is wrong, check again");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "your email or password is wrong");
        }
    }

    @Transactional
    public LogoutResponse logout(User user){
        user.setToken(null);
        user.setTokenExpiredAt(null);
        user.setUpdateAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
        userRepository.save(user);

        return LogoutResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .updateAt(user.getUpdateAt())
                .build();
    }
}

package org.ecommerce.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    private String id;

    private String name;

    @Column(name = "birth_day")
    private String birthDay;

    private String gender;

    @Column(name = "profile_picture")
    private String profilePicture;

    private String email;

    @Column(name = "email_otp")
    private String emailOtp;

    @Column(name = "email_verification")
    private Boolean emailVerification;

    private String handphone;

    @Column(name = "handphone_otp")
    private String handphoneOtp;

    @Column(name = "handphone_verification")
    private Boolean handphoneVerification;

    private String password;

    private String token;

    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;

    @Column(name = "update_at")
    private String updateAt;

    @Column(name = "created_at")
    private String createdAt;
}

package org.ecommerce.api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String name;

    private String birthDay;

    private String gender;

    private String profilePicture;

    private String email;

    private Boolean emailVerification;

    private String updateAt;

    private String createdAt;
}

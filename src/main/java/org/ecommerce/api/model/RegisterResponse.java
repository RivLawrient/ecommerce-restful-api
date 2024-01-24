package org.ecommerce.api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {

    private String email;

    private String name;

    private String createdAt;
}

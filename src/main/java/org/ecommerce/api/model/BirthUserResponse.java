package org.ecommerce.api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BirthUserResponse {

    private String email;

    private String name;

    private String birthDay;

    private String updateAt;
}

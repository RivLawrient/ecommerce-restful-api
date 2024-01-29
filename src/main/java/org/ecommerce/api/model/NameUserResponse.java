package org.ecommerce.api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NameUserResponse {

    private String name;

    private String email;

    private String updateAt;
}

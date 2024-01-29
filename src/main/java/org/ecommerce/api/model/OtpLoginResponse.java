package org.ecommerce.api.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtpLoginResponse {

    private String email;

    private String name;

    private String upadateAt;
}

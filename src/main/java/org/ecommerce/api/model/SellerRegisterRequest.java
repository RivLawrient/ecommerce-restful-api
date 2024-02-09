package org.ecommerce.api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerRegisterRequest {

    private String name;

    private String domain;

    private String province;

    private String city;

    private String postalCode;

}

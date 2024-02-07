package org.ecommerce.api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {

    private String id;

    private String name;

    private String handphone;

    private String label;

    private String city;

    private String province;

    private String postalCode;

    private String completeAddress;

    private String coordinate;

    private String note;

    private Boolean mainAddress;
}

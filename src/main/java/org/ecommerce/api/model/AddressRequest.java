package org.ecommerce.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRequest {

    @Size(max = 100)
    private String name;

    private String handphone;

    @Size(max = 255)
    private String label;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String province;

    @Size(max = 255)
    private String postalCode;

    @Size(max = 255)
    private String completeAddress;

    @Size(max = 255)
    private String coordinate;

    @Size(max = 255)
    private String note;

    @NonNull
    private Boolean mainAddress;
}

package org.ecommerce.api.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class BirthUserRequest {

    @NonNull
    private Integer day;

    @NonNull
    private Integer month;

    @NonNull
    private Integer year;
}

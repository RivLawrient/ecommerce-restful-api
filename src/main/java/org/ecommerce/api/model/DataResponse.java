package org.ecommerce.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataResponse<T> {

    private String api_version;
    private Integer status_code;
    private String message;
    private T data;
    private String timestamp;
    private String path;
}

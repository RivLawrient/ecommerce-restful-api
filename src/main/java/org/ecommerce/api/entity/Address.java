package org.ecommerce.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;

    private String handphone;

    private String label;

    private String city;

    private String province;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "complete_address")
    private String completeAddress;

    private String coordinate;

    private String notes;

    @Column(name = "main_address")
    private Boolean mainAddress;
}

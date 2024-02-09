package org.ecommerce.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seller")
public class Seller {

    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Long balance;

    @Column(name = "profile_picture")
    private String profilePicture;

    private String name;

    private String description;

    private String domain;

    private String city;

    private String province;

    @Column(name = "complete_address")
    private String completeAddress;

    @Column(name = "postal_code")
    private String postalCode;

    private String coordinate;

    @Column(name = "status_active")
    private Boolean statusActive;

    @Column(name = "last_seen")
    private Long lastSeen;

    @Column(name = "created_at")
    private String createdAt;
}

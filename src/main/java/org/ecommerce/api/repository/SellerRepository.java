package org.ecommerce.api.repository;

import org.ecommerce.api.entity.Seller;
import org.ecommerce.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

    Optional<Seller> findByUser(User user);
}

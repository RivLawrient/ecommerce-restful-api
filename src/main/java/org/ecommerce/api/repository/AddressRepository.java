package org.ecommerce.api.repository;

import org.ecommerce.api.entity.Address;
import org.ecommerce.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

    Optional<Address> findByIdAndUser(String id, User user);
}

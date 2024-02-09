package org.ecommerce.api.service;

import org.ecommerce.api.entity.Seller;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.SellerRegisterRequest;
import org.ecommerce.api.model.SellerRegisterResponse;
import org.ecommerce.api.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ValidateService validateService;

    @Transactional
    public SellerRegisterResponse register(User user, SellerRegisterRequest request){
        if(!sellerRepository.findByUser(user).isPresent()){
            Seller seller = new Seller();
            seller.setId(UUID.randomUUID().toString());
            seller.setUser(user);
            seller.setBalance(0L);
            seller.setName(request.getName());
            seller.setDomain(request.getDomain());
            seller.setProvince(request.getProvince());
            seller.setCity(request.getCity());
            seller.setPostalCode(request.getPostalCode());
            seller.setCreatedAt(String.valueOf(new Timestamp(System.currentTimeMillis())));
            sellerRepository.save(seller);

            return SellerRegisterResponse.builder()
                    .id(seller.getId())
                    .name(seller.getName())
                    .domain(seller.getDomain())
                    .province(seller.getProvince())
                    .city(seller.getCity())
                    .postalCode(seller.getPostalCode())
                    .createdAt(seller.getCreatedAt())
                    .build();
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "your user was a seller");
        }
    }
}

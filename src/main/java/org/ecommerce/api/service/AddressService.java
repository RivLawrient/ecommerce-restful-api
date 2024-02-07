package org.ecommerce.api.service;

import org.ecommerce.api.entity.Address;
import org.ecommerce.api.entity.User;
import org.ecommerce.api.model.AddressRequest;
import org.ecommerce.api.model.AddressResponse;
import org.ecommerce.api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ValidateService validateService;

    @Transactional
    public AddressResponse add(User user, AddressRequest request){
        validateService.validate(request);

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setUser(user);
        address.setName(request.getName());
        address.setHandphone(request.getHandphone());
        address.setLabel(request.getLabel());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setPostalCode(request.getPostalCode());
        address.setCompleteAddress(request.getCompleteAddress());
        address.setCoordinate(request.getCoordinate());
        address.setNotes(request.getNote());
        address.setMainAddress(request.getMainAddress());
        addressRepository.save(address);

        return response(address);
    }

    private AddressResponse response(Address address){
        return AddressResponse.builder()
                .id(address.getId())
                .name(address.getName())
                .handphone(address.getHandphone())
                .label(address.getLabel())
                .city(address.getCity())
                .province(address.getProvince())
                .postalCode(address.getPostalCode())
                .completeAddress(address.getCompleteAddress())
                .coordinate(address.getCoordinate())
                .note(address.getNotes())
                .mainAddress(address.getMainAddress())
                .build();
    }
}

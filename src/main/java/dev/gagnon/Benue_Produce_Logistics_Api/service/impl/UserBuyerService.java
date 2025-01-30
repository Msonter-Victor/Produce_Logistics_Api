package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;


import dev.gagnon.Benue_Produce_Logistics_Api.config.MapperConfig;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Buyer;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BuyerRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.UserNotFoundException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.BuyerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.sendMail;
import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.validateDetails;


@Service
public class UserBuyerService implements BuyerService {
    private final MapperConfig mapperConfig;
    private final BioDataRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuyerRepository buyerRepository;

    public UserBuyerService(MapperConfig mapperConfig, BioDataRepository userRepository, PasswordEncoder passwordEncoder, BuyerRepository buyerRepository) {
        this.mapperConfig = mapperConfig;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.buyerRepository = buyerRepository;
    }

    @Override
    public RegistrationResponse register(RegisterRequest request) {
        validateDetails(request);
        BioData user = mapperConfig.toBioData(request);
        user.setRoles(Collections.singleton(request.getRole()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        Buyer buyer =  new Buyer();
        buyer.setBioData(user);
        buyerRepository.save(buyer);
        //sendMail(user);
        // Prepare response
        RegistrationResponse response = new RegistrationResponse();
        response.setMessage("Successfully registered");
        return response;
    }

    @Override
    public Buyer findByEmail(String email) {
        return buyerRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email + " not found"));
    }

    @Override
    public Buyer findById(UUID buyerId) {
        return buyerRepository.findById(buyerId)
                .orElseThrow(()->new UserNotFoundException("user not found"));
    }
}

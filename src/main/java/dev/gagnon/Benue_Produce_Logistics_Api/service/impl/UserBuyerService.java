package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;


import dev.gagnon.Benue_Produce_Logistics_Api.config.MapperConfig;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.AccountDetails;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Buyer;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.AccountDetailsRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BuyerRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddAccountDetailsRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.AddAccountDetailsResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.InvalidDataException;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.UserNotFoundException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.BuyerService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.sendMail;
import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.validateDetails;
import static java.time.LocalDateTime.now;


@Service
public class UserBuyerService implements BuyerService {
    private final MapperConfig mapperConfig;
    private final BioDataRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuyerRepository buyerRepository;
    private final ModelMapper modelMapper;
    private final AccountDetailsRepository accountDetailsRepository;

    public UserBuyerService(MapperConfig mapperConfig, BioDataRepository userRepository, PasswordEncoder passwordEncoder, BuyerRepository buyerRepository, ModelMapper modelMapper, AccountDetailsRepository accountDetailsRepository) {
        this.mapperConfig = mapperConfig;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.buyerRepository = buyerRepository;
        this.modelMapper = modelMapper;
        this.accountDetailsRepository = accountDetailsRepository;
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
        sendMail(user);
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

    @Override
    public AddAccountDetailsResponse addAccountDetails(AddAccountDetailsRequest request,String email) {
        validate(request.getAccountNumber());
        Buyer buyer = findByEmail(email);
        AccountDetails newAccountDetails = modelMapper.map(request, AccountDetails.class);
        newAccountDetails.setBuyer(buyer);
        newAccountDetails = accountDetailsRepository.save(newAccountDetails);
        AddAccountDetailsResponse response = modelMapper.map(newAccountDetails, AddAccountDetailsResponse.class);
        response.setId(buyer.getId());
        response.setResponseTime(now());
        return response;
    }

    private void validate(String accountNumber) {
        String regex = "\\d{10}";
        if (!accountNumber.matches(regex)) throw new InvalidDataException("Invalid account number");
    }
}

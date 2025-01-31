package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;


import dev.gagnon.Benue_Produce_Logistics_Api.config.MapperConfig;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Farmer;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.FarmerRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.FarmerResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.UserNotFoundException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.FarmerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.sendMail;
import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.validateDetails;


@Service
public class BdicFarmerService implements FarmerService {
    private final FarmerRepository farmerRepository;
    private final BioDataRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MapperConfig mapperConfig;

    public BdicFarmerService(FarmerRepository farmerRepository, BioDataRepository bioDataRepository, PasswordEncoder passwordEncoder, MapperConfig mapperConfig) {
        this.farmerRepository = farmerRepository;
        this.userRepository = bioDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapperConfig = mapperConfig;
    }


    @Override
    public RegistrationResponse register(RegisterRequest request) {
        validateDetails(request);
        BioData user = mapperConfig.toBioData(request);
        user.setRoles(Collections.singleton(request.getRole()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        Farmer farmer =  new Farmer();
        farmer.setBioData(user);
        farmerRepository.save(farmer);
        sendMail(user);
        RegistrationResponse response = new RegistrationResponse();
        response.setMessage("Successfully registered");
        return response;
    }

    @Override
    public FarmerResponse findByEmail(String email) {
        Farmer farmer = farmerRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("User not found with email: " + email));
        return new FarmerResponse(farmer);
    }
}

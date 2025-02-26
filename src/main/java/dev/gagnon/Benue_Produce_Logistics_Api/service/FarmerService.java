package dev.gagnon.Benue_Produce_Logistics_Api.service;


import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Farmer;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;

import java.util.UUID;

public interface FarmerService {
    RegistrationResponse register(RegisterRequest request);

    Object findByEmail(String email);

    Farmer findById(UUID farmerId);
}

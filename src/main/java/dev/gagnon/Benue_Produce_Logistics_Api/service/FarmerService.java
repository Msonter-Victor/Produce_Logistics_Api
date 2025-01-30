package dev.gagnon.Benue_Produce_Logistics_Api.service;


import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;

public interface FarmerService {
    RegistrationResponse register(RegisterRequest request);

    Object findByEmail(String email);
}

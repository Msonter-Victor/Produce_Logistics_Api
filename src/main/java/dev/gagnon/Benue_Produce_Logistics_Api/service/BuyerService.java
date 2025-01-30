package dev.gagnon.Benue_Produce_Logistics_Api.service;


import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Buyer;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;

import java.util.UUID;

public interface BuyerService {
    RegistrationResponse register(RegisterRequest request);

    Buyer findByEmail(String email);

    Buyer findById(UUID buyerId);
}

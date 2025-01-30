package dev.gagnon.Benue_Produce_Logistics_Api.service;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.LoginRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.LoginResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegisterResponse;

public interface UserService {
    LoginResponse login(LoginRequest request);
    void logout(String token);
}

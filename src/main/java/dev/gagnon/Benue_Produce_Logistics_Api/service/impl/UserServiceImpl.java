package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;


import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.LoginRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.LoginResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.security.service.BlacklistService;
import dev.gagnon.Benue_Produce_Logistics_Api.security.service.JwtService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BlacklistService blacklistService;


    @Override
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        // Generate token
        String accessToken = jwtService.generateJwtToken(request.getUsername());
        return new LoginResponse(accessToken);
    }

    @Override
    public void logout(String token) {
        if (token != null && !blacklistService.isTokenBlacklisted(token)) {
            blacklistService.blacklistToken(token);
        }
    }
}

package dev.gagnon.Benue_Produce_Logistics_Api.security.service;

import dev.gagnon.Benue_Produce_Logistics_Api.security.data.models.BlackListedToken;
import dev.gagnon.Benue_Produce_Logistics_Api.security.data.repository.BlackTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacklistService {
    private final BlackTokenRepository blacklistedTokenRepository;

    @Autowired
    public BlacklistService(BlackTokenRepository blacklistedTokenRepository) {
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }

    // Method to blacklist a token
    public void blacklistToken(String token) {
        BlackListedToken blacklistedToken = new BlackListedToken(token);
        blacklistedTokenRepository.save(blacklistedToken);
    }

    // Method to check if a token is blacklisted
    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokenRepository.findByToken(token).isPresent();  // Check if the token exists in the database
    }
}

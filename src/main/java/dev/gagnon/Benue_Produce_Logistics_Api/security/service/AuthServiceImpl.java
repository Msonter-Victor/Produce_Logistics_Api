package dev.gagnon.Benue_Produce_Logistics_Api.security.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.UploadPhotoRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.security.data.models.BlacklistedToken;
import dev.gagnon.Benue_Produce_Logistics_Api.security.data.repository.BlackTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.getMediaUrls;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.HOURS;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final BlackTokenRepository blacklistedTokenRepository;


    @Autowired
    public AuthServiceImpl(BlackTokenRepository blacklistedTokenRepository) {
        this.blacklistedTokenRepository = blacklistedTokenRepository;
    }


    @Override
    public void blacklist(String token) {
        log.info("Trying to blacklist token: {}", token);
        trackExpiredTokens();
        BlacklistedToken blacklistedToken = new BlacklistedToken();
        blacklistedToken.setToken(token);
        blacklistedToken.setExpiresAt(now().plus(24, HOURS));
        blacklistedTokenRepository.save(blacklistedToken);
        log.info("Blacklisted token: {}", token);
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        log.info("Checking blacklist status of token: {}", token);
        boolean isBlacklisted = blacklistedTokenRepository.existsByToken(token);
        log.info("Blacklist status of token: {}", isBlacklisted);
        trackExpiredTokens();
        return isBlacklisted;
    }



    private void trackExpiredTokens() {
        log.info("Tracking and deleting expired user tokens");
        var blacklist = blacklistedTokenRepository.findAll();
        blacklist.stream()
                .filter(blacklistedToken -> now().isAfter(blacklistedToken.getExpiresAt()))
                .forEach(blacklistedTokenRepository::delete);
        log.info("Expired user tokens successfully tracked and deleted");
    }
}

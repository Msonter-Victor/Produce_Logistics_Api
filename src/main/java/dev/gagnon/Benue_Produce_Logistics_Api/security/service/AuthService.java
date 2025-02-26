package dev.gagnon.Benue_Produce_Logistics_Api.security.service;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.UploadPhotoRequest;

public interface AuthService {
    void blacklist(String token);
    boolean isTokenBlacklisted(String token);

}

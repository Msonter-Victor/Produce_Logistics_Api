package dev.gagnon.Benue_Produce_Logistics_Api.service;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.UploadPhotoRequest;

public interface UserService {
    String uploadProfilePhoto(UploadPhotoRequest request, String email);
}

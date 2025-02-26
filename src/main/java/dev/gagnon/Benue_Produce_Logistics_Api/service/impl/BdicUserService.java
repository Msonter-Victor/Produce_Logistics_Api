package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.UploadPhotoRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.UserNotFoundException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.getMediaUrls;

@Service
public class BdicUserService implements UserService {
    private final Cloudinary cloudinary;
    private final BioDataRepository bioDataRepository;

    public BdicUserService( Cloudinary cloudinary, BioDataRepository bioDataRepository) {
        this.cloudinary = cloudinary;
        this.bioDataRepository = bioDataRepository;
    }

    @Override
    public String uploadProfilePhoto(UploadPhotoRequest request, String email) {
        BioData bioData = bioDataRepository.findByEmail(email).orElseThrow(()->new UserNotFoundException("user not found"));
        Uploader uploader = cloudinary.uploader();
        String photoUrl = getMediaUrls(List.of(request.getPhotoUrl()),uploader)
                .getFirst();
        bioData.setImageUrl(photoUrl);
        bioDataRepository.save(bioData);
        return photoUrl;
    }
}

package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UploadPhotoRequest {
    private MultipartFile photoUrl;
}

package dev.gagnon.Benue_Produce_Logistics_Api.controller;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.UploadPhotoRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/uploadProfilePhoto", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadProfilePhoto(@ModelAttribute UploadPhotoRequest request, Principal principal){
        try{
            String email = principal.getName();
            String response = userService.uploadProfilePhoto(request, email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (BdicBaseException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

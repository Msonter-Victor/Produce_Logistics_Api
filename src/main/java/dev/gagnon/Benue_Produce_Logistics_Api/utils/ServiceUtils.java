package dev.gagnon.Benue_Produce_Logistics_Api.utils;

import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.SendMailRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.EmailExistsException;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.UploadMediaFailedException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ServiceUtils {
    private static final String SENDER_EMAIL = "benproduce@gmail.com";
    private static BioDataRepository userRepository;
    private static EmailSenderService emailSenderService;

    public ServiceUtils(BioDataRepository userRepository, EmailSenderService emailSenderService) {
        ServiceUtils.userRepository = userRepository;
        ServiceUtils.emailSenderService = emailSenderService;
    }

    public static List<String> getMediaUrls(List<MultipartFile> mediaFiles, Uploader uploader) {
        log.info("Starting batch upload to Cloudinary");
        return mediaFiles.stream()
                .map(mediaFile -> {
                    try {
                        Map<?, ?> map = ObjectUtils.asMap(
                                "resource_type", "image",
                                "use_filename", true
                        );
                        Map<?, ?> uploadResponse = uploader.upload(mediaFile.getBytes(), map);
                        log.info("Image uploaded successfully: {}", uploadResponse.get("url"));
                        return uploadResponse.get("url").toString(); // Collect the URL
                    } catch (Exception exception) {
                        log.error("Error while uploading image to Cloudinary: {}", mediaFile.getOriginalFilename(), exception);
                        throw new UploadMediaFailedException("Failed to upload media: "+ exception);
                    }
                })
                .collect(Collectors.toList());
    }



    public static void validateDetails(RegisterRequest request) {
        validateEmail(request);
    }

    private static void validateEmail(RegisterRequest request) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!request.getEmail().matches(emailRegex))
            throw new BdicBaseException("Invalid email format");
        validateExistingEmail(request);
    }


    private static void validateExistingEmail(RegisterRequest request) {
        boolean emailExists = userRepository.existsByEmail(request.getEmail());
        if (emailExists) throw new EmailExistsException(request.getEmail() + " already exists");
    }

    public static void sendMail(BioData user) {
        // Prepare email content
        String content = buildConfirmationEmail(user.getFirstName());
        // Send email
        SendMailRequest sendMailRequest = buildMailRequest(user, content);

        try {
            emailSenderService.sendEmail(sendMailRequest);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send welcome email", e);
        }
    }

    private static SendMailRequest buildMailRequest(BioData user, String content) {
        SendMailRequest sendMailRequest = new SendMailRequest();
        sendMailRequest.setRecipientEmail(user.getEmail());
        sendMailRequest.setRecipientName(user.getFirstName());
        sendMailRequest.setSenderEmail(SENDER_EMAIL);
        sendMailRequest.setContent(content);
        return sendMailRequest;
    }



    private static String buildConfirmationEmail(String name) {
        return "<p>Hello " + name + ",</p>"
                + "<p>Thank you for registering with Benue State Produce and Logistics Services.</p>"
                + "<p>We are happy to have you onboard</p>"
                + "<p>If you did not register, please ignore this email.</p>"
                + "<p>Regards</p>"
                + "<p>Benue State Produce and Logistics Services.</p>";
    }

}

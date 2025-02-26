package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductRequest {
    private String productName;
    private Long stock;
    private Long unitPrice;
    private String description;
    private List<MultipartFile> images;

}


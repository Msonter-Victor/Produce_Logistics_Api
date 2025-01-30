package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;


import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Product;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private UUID productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private FarmerResponse farmerResponse;
    private List<String> imageUrls;

    public ProductResponse(Product product) {
        this.productId = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrls = product.getImageUrls();
        this.farmerResponse = new FarmerResponse(product.getFarmer());
    }
}

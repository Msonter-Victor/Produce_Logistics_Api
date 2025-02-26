package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;


import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Product;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private UUID id;
    private String productName;
    private String description;
    private Long stock;
    private Long price;
    private FarmerResponse farmerResponse;
    private List<String> imageUrls;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.description = product.getDescription();
        this.stock = product.getStock();
        this.price = product.getUnitPrice();
        this.imageUrls = product.getImageUrls();
        this.farmerResponse = new FarmerResponse(product.getFarmer());
    }
}

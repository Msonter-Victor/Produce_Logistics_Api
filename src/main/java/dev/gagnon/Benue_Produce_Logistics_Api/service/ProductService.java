package dev.gagnon.Benue_Produce_Logistics_Api.service;



import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Product;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddProductRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.AddProductResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest request, String email);
    ProductResponse viewProduct(UUID productId);
    List<ProductResponse> viewAllProducts();
    String deleteProduct(UUID productId);

    Product getProductById(UUID productId);

    void saveProduct(Product product);
}

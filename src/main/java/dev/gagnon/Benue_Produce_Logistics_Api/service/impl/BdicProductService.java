package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;

import dev.gagnon.Benue_Produce_Logistics_Api.config.MapperConfig;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Farmer;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Product;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.FarmerRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.ProductRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddProductRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.AddProductResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.ProductResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.ProductNotFoundException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.getMediaUrls;


@Service
public class BdicProductService implements ProductService {
    private final ProductRepository productRepository;
    private final MapperConfig mapperConfig;
    private final FarmerRepository farmerRepository;
    private final Cloudinary cloudinary;

    public BdicProductService(ProductRepository productRepository, MapperConfig mapperConfig, FarmerRepository farmerRepository, Cloudinary cloudinary) {
        this.productRepository = productRepository;
        this.mapperConfig = mapperConfig;
        this.farmerRepository = farmerRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public AddProductResponse addProduct(AddProductRequest request, String email) {
        // Map request to Product entity
        Product product = mapperConfig.toProduct(request);
        product.setUnitPrice(request.getUnitPrice());
        Farmer user = farmerRepository.findByEmail(email).
                orElseThrow(()->new ProductNotFoundException(email));
        Uploader uploader = cloudinary.uploader();
        product.setFarmer(user);
        List<String> mediaUrls = getMediaUrls(request.getImages(), uploader);
        product.setImageUrls(mediaUrls);
        productRepository.save(product);

        AddProductResponse response = new AddProductResponse();
        response.setProductId(product.getId());
        response.setMessage("Successfully added product");
        return response;
    }

    @Override
    public ProductResponse viewProduct(UUID productId) {
        Product product = getProductById(productId);
        return new ProductResponse(product);
    }

    @Override
    public List<ProductResponse> viewAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new ArrayList<>();
        }
        return products.stream().map(
                ProductResponse::new).toList();
    }

    @Override
    public String deleteProduct(UUID productId) {
        Product product = getProductById(productId);
        productRepository.delete(product);
        return "Successfully deleted product";
    }


    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> findFarmerProducts(String email) {
        List<Product> products = productRepository.findByFarmer(email);
        return products.stream().map(
                ProductResponse::new).toList();    }

    @Override
    public Product getProductById(UUID productId) {
        return productRepository.findById(productId).
                orElseThrow(()->new ProductNotFoundException("product not found"));
    }


}

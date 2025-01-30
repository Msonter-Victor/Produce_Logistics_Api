package dev.gagnon.Benue_Produce_Logistics_Api.controller;


import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddProductRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping(path = "/add", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addProduct(@ModelAttribute AddProductRequest request, Principal principal) {
        try {
            String email = principal.getName();
            var response = productService.addProduct(request, email);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (BdicBaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBy/{id}")
    public ResponseEntity<?> getProduct(@PathVariable UUID id) {
        try{
            var response = productService.viewProduct(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (BdicBaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/findAll")
    public ResponseEntity<?> getAllProducts() {
        var response = productService.viewAllProducts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable UUID id) {
        try{
            String response = productService.deleteProduct(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (BdicBaseException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

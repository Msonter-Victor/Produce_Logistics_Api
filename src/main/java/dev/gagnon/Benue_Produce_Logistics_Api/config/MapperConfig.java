package dev.gagnon.Benue_Produce_Logistics_Api.config;


import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Product;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddProductRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import org.mapstruct.Mapper;

@Mapper
public interface MapperConfig {
    BioData toBioData(RegisterRequest registerRequest);

    Product toProduct(AddProductRequest addProductRequest);
}

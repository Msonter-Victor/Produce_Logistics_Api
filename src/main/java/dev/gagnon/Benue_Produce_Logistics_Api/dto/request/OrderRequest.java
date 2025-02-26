package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderRequest {
    private double amount;
    private List<Product> products;
}

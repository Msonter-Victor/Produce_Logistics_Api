package dev.gagnon.Benue_Produce_Logistics_Api.service;


import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Order;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddToOrderRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.OrderRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RemoveProductRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.AddOrderResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.OrderResponse;

import java.util.UUID;

public interface OrderService {
    AddOrderResponse addProductToOrder(AddToOrderRequest request,String email);
    String removeProductFromOrder(RemoveProductRequest request);
    Order findById(UUID orderId);

    void save(Order order);

}

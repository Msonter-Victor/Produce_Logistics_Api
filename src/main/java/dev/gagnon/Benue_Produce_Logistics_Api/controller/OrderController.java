package dev.gagnon.Benue_Produce_Logistics_Api.controller;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddToOrderRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.OrderRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RemoveProductRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.AddOrderResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.OrderResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("addToOrder")
    public ResponseEntity<?> addProductToOrder(@RequestBody AddToOrderRequest request, Principal principal) {
        try{
            String email = principal.getName();
            AddOrderResponse response = orderService.addProductToOrder(request,email);
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/removeProductFromOrder")
    public ResponseEntity<?> removeProductFromOrder(@RequestBody RemoveProductRequest request){
        try{
            String response = orderService.removeProductFromOrder(request);
            return ResponseEntity.ok(response);
        }
        catch(BdicBaseException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

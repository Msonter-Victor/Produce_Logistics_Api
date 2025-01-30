package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Buyer;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Order;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.OrderItem;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Product;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.OrderRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddToOrderRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RemoveProductRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.AddOrderResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.service.BuyerService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.OrderService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BdicOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final BuyerService buyerService;
    private final ProductService productService;

    public BdicOrderService(OrderRepository orderRepository, BuyerService buyerService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.buyerService = buyerService;
        this.productService = productService;
    }

    @Override
    public AddOrderResponse addProductToOrder(AddToOrderRequest request,String email) {
        Order order = getOrder(request,email);

        // Retrieve the product to be added
        Product product = productService.getProductById(request.getProductId());

        // Check if the product is already in the order
        OrderItem existingItem = order.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Update quantity if the product exists in the order
            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
        } else {
            OrderItem newItem = new OrderItem();
            newItem.setOrder(order);
            newItem.setProduct(product);
            newItem.setQuantity(request.getQuantity());
            order.getItems().add(newItem);
        }

        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock for product: " + product.getProductName());
        }
        product.setStock(product.getStock() - request.getQuantity());

        // Save the order and update the product
        orderRepository.save(order);
        productService.saveProduct(product);

        // Build and return the response
        AddOrderResponse response = new AddOrderResponse();
        response.setOrderId(order.getId());
        response.setMessage("Product successfully added to order");
        return response;
    }

    @Override
    public String removeProductFromOrder(RemoveProductRequest request) {
        Order order = orderRepository.findById(request.getOrderId()).orElse(null);
        Product product = productService.getProductById(request.getProductId());
        OrderItem existingItem = order.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(request.getProductId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null && existingItem.getQuantity() > request.getQuantity()) {

            existingItem.setQuantity(existingItem.getQuantity() - request.getQuantity());
            product.setStock(product.getStock() + request.getQuantity());
        }
        if (existingItem.getQuantity()==0){
            order.getItems().remove(existingItem);
        }

        orderRepository.save(order);
        productService.saveProduct(product);

        return "Product successfully removed from order";
    }

    private Order getOrder(AddToOrderRequest request,String email) {
        return orderRepository.findById(request.getOrderId())
                .orElseGet(() -> {
                    Order newOrder = new Order();
                    Buyer buyer = buyerService.findByEmail(email);
                    newOrder.setBuyer(buyer);
                    return orderRepository.save(newOrder);
                });
    }

    @Override
    public Order findById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElse(null);
    }
}

package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;

import dev.gagnon.Benue_Produce_Logistics_Api.config.PaymentConfig;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Buyer;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Farmer;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Order;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.PaymentRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.TransferResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.service.BuyerService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.FarmerService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.OrderService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BdicPaymentService implements PaymentService {
    private final PaymentConfig paymentConfig;
    private final OrderService orderService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final BuyerService buyerService;


    public BdicPaymentService(PaymentConfig paymentConfig, OrderService orderService, BuyerService buyerService) {
        this.paymentConfig = paymentConfig;
        this.orderService = orderService;
        this.buyerService = buyerService;
    }

    @Override
    public TransferResponse initiateTransfer(PaymentRequest request, String email) {
        Order order = orderService.findById(request.getOrderId());
        String url = paymentConfig.getApiBaseUrl() + "/transaction/initialize";

        Map<String, Object> requestData = new HashMap<>();
        requestData.put("amount", request.getAmount());
        requestData.put("currency", "NGN");
        requestData.put("email", email);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", paymentConfig.getPublicKey());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestData, headers);
        ResponseEntity<TransferResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, TransferResponse.class);
        Buyer buyer = buyerService.findByEmail(email);
        order.setBuyer(buyer);
        orderService.save(order);

        return response.getBody();
    }




}

package dev.gagnon.Benue_Produce_Logistics_Api.controller;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.PaymentRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.TransferResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody PaymentRequest request, Principal principal) {
        try{
            String email = principal.getName();
            TransferResponse response = paymentService.initiateTransfer(request, email);
            return ResponseEntity.ok(response);
        }
        catch (BdicBaseException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}

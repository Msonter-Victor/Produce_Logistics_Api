package dev.gagnon.Benue_Produce_Logistics_Api.controller;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.*;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.UpdateLocationResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.LogisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/logistics")
public class LogisticsController {
    private final LogisticService logisticService;


    public LogisticsController(LogisticService logisticService) {
        this.logisticService = logisticService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            RegistrationResponse register = logisticService.register(registerRequest);
            return new ResponseEntity<>(register, HttpStatus.CREATED);
        }
        catch (BdicBaseException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/findAvailableRiders")
    public ResponseEntity<?> findAvailableRiders(@RequestBody FindRiderRequest request) {
        try {
            var response = logisticService.findAvailableRiders(request.getLatitude(), request.getLongitude());
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/orderRide")
    public ResponseEntity<?> orderRide(@RequestBody OrderRideRequest orderRideRequest, Principal principal) {
        try{
            String response = logisticService.orderRide(orderRideRequest);
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping("/updateLocation")
    public ResponseEntity<?> updateLocation(@RequestBody UpdateLocationRequest updateLocationRequest, Principal principal) {
        try{
            String email = principal.getName();
            UpdateLocationResponse response = logisticService.updateLocation(updateLocationRequest, email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/confirmDelivery")
    public ResponseEntity<?> confirmDelivery(@RequestBody ConfirmDeliveryRequest confirmDeliveryRequest) {
        try {
            String response = logisticService.confirmDelivery(confirmDeliveryRequest);
            return ResponseEntity.ok(response);
        }
        catch (BdicBaseException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/sendDeliveryConfirmation")
    public ResponseEntity<?> sendDeliveryConfirmation(@RequestBody DeliveryRequest request) {
        try {
            String response = logisticService.sendConfirmationRequest(request);
            return ResponseEntity.ok(response);
        }
        catch (BdicBaseException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

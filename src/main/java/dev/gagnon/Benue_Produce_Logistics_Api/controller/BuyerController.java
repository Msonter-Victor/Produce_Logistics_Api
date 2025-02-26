package dev.gagnon.Benue_Produce_Logistics_Api.controller;



import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.AddAccountDetailsRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.AddAccountDetailsResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.BuyerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/buyer")
public class BuyerController {
    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try{
            var response = buyerService.register(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (BdicBaseException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addAccountDetails")
    public ResponseEntity<?> addAccountDetails(@ModelAttribute AddAccountDetailsRequest request, Principal principal) {
        try{
            String email = principal.getName();
            AddAccountDetailsResponse response = buyerService.addAccountDetails(request, email);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (BdicBaseException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}

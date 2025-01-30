package dev.gagnon.Benue_Produce_Logistics_Api.controller;


import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.FarmerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/farmer")
public class FarmerController {
    private final FarmerService farmerService;

    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try{
            var response = farmerService.register(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (BdicBaseException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findBy/{email}")
    public ResponseEntity<?> getFarmerByEmail(@PathVariable String email) {
        try{
            var response = farmerService.findByEmail(email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (BdicBaseException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

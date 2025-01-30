package dev.gagnon.Benue_Produce_Logistics_Api.controller;


import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.LoginRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.RegisterRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.BdicBaseException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try{
            var response = userService.login(loginRequest);
            return ResponseEntity.ok(response);
        }
        catch (BdicBaseException exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        String token = authHeader.substring(7);
        userService.logout(token);
        return ResponseEntity.ok("Logged out successfully");
    }
}

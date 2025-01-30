package dev.gagnon.Benue_Produce_Logistics_Api.service;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.*;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RiderResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.UpdateLocationResponse;

import java.util.List;

public interface LogisticService {
    RegistrationResponse register(RegisterRequest registerRequest);
    double calculateDistance(double lat1, double lat2, double lon1, double lon2);
    List<RiderResponse> findAvailableRiders(double latitude, double longitude);
    UpdateLocationResponse updateLocation(UpdateLocationRequest request,String email);
    String orderRide(OrderRideRequest request);
    String confirmDelivery(ConfirmDeliveryRequest request);
    String sendConfirmationRequest(DeliveryRequest request);
}

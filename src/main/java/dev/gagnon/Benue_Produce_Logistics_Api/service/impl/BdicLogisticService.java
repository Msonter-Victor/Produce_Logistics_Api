package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;

import dev.gagnon.Benue_Produce_Logistics_Api.config.MapperConfig;
import dev.gagnon.Benue_Produce_Logistics_Api.data.constants.RideStatus;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.*;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.LogisticRiderRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.RideRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.*;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RegistrationResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.RiderResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.response.UpdateLocationResponse;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.RideNotFoundException;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.UserNotFoundException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.BuyerService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.LogisticService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.NotificationService;
import dev.gagnon.Benue_Produce_Logistics_Api.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static dev.gagnon.Benue_Produce_Logistics_Api.utils.LocationUtils.EARTH_RADIUS;
import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.sendMail;
import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.validateDetails;

@Slf4j
@Service
public class BdicLogisticService implements LogisticService {
    private final LogisticRiderRepository riderRepository;
    private final MapperConfig mapperConfig;
    private final BioDataRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BuyerService buyerService;
    private final OrderService orderService;
    private final RideRepository rideRepository;
    private final NotificationService notificationService;

    public BdicLogisticService(LogisticRiderRepository riderRepository, MapperConfig mapperConfig, BioDataRepository bioDataRepository, PasswordEncoder passwordEncoder, BuyerService buyerService, OrderService orderService, RideRepository rideRepository, NotificationService notificationService) {
        this.riderRepository = riderRepository;
        this.mapperConfig = mapperConfig;
        this.userRepository = bioDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.buyerService = buyerService;
        this.orderService = orderService;
        this.rideRepository = rideRepository;
        this.notificationService = notificationService;
    }

    @Override
    public RegistrationResponse register(RegisterRequest request) {
        validateDetails(request);
        BioData user = mapperConfig.toBioData(request);
        user.setRoles(Collections.singleton(request.getRole()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        LogisticsProvider rider =  new LogisticsProvider();
        rider.setBioData(user);
        riderRepository.save(rider);
        //sendMail(user);
        RegistrationResponse response = new RegistrationResponse();
        response.setMessage("Successfully registered");
        return response;
    }

    @Override
    public double calculateDistance(double lat1, double lat2, double lon1, double lon2) {
        // Convert degrees to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);

        double latDiff = lat2 - lat1;
        double lonDiff = lon2 - lon1;
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double result = EARTH_RADIUS * c; // EARTH_RADIUS should be in km (6371) or miles (3958.8)
        log.info("Distance: " + result);
        System.out.println(result);
        return result;
    }

    @Override
    public List<RiderResponse> findAvailableRiders(double latitude, double longitude) {
        List<LogisticsProvider> riders = riderRepository.findAll();
        List<LogisticsProvider> availableRiders = riders.stream()
                .filter(rider -> calculateDistance(
                        rider.getLatitude(), latitude, rider.getLongitude(),longitude) < 1000)
                .toList();
        return availableRiders.stream()
                .map(RiderResponse::new).toList();
    }



    @Override
    public UpdateLocationResponse updateLocation(UpdateLocationRequest request,String email) {
        LogisticsProvider rider = riderRepository.findByEmail(email)
                .orElseThrow(()->new UserNotFoundException("User not found"));
        rider.setLatitude(request.getLatitude());
        rider.setLongitude(request.getLongitude());
        riderRepository.save(rider);
        UpdateLocationResponse response = new UpdateLocationResponse();
        response.setMessage("Successfully updated");
        return response;
    }

    @Override
    public String orderRide(OrderRideRequest request) {
        Ride ride = new Ride();
        LogisticsProvider rider = riderRepository.findById(request.getRiderId())
                .orElseThrow(()->new UserNotFoundException("Rider not found"));
        Buyer buyer = buyerService.findById(request.getBuyerId());
        Order order = orderService.findById(request.getOrderId());
        ride.setRider(rider);
        ride.setBuyer(buyer);
        ride.setOrder(order);
        ride.setStatus(RideStatus.PENDING);
        return "order sent to "+ rider.getBioData().getFirstName();
    }

    @Override
    public String confirmDelivery(ConfirmDeliveryRequest request) {
        Ride ride = rideRepository.findById(request.getRideId())
                .orElseThrow(()->new RideNotFoundException("ride not found"));
        ride.setStatus(RideStatus.DELIVERED);
        return "Delivered successfully";
    }

    @Override
    public String sendConfirmationRequest(DeliveryRequest request) {
        Ride ride = rideRepository.findById(request.getOrderId()).orElseThrow();
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setRecipientId(ride.getBuyer().getId());
        notificationRequest.setMessage("Dear "+ride.getBuyer().getBioData().getFirstName()+ ", Please confirm your delivery");
        notificationService.sendNotification(notificationRequest);
        return notificationRequest.getMessage();
    }


}

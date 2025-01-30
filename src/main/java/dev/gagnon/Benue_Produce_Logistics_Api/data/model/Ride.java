package dev.gagnon.Benue_Produce_Logistics_Api.data.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import dev.gagnon.Benue_Produce_Logistics_Api.data.constants.RideStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    private LogisticsProvider rider;

    @ManyToOne(cascade = CascadeType.ALL)
    private Buyer buyer;

    @OneToOne(cascade = CascadeType.ALL)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideStatus status;

    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime deliveryDate;

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
    }

}

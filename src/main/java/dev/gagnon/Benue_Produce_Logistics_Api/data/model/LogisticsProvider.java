package dev.gagnon.Benue_Produce_Logistics_Api.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_riders")
public class LogisticsProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "bio_data_id")
    private BioData bioData;
    private double longitude;
    private double latitude;
}

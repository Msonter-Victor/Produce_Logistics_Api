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
@Table(name = "_buyers")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne
    @JoinColumn(name = "bio_data_id")
    private BioData bioData;
}

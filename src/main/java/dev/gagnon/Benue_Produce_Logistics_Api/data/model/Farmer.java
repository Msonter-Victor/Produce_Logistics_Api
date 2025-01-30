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
@Table(name = "_farmers")
public class Farmer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne
    private BioData bioData;
}

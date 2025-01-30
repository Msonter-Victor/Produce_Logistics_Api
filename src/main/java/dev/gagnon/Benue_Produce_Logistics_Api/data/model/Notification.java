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
@Table(name = "_notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String message;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private BioData recipient;

}

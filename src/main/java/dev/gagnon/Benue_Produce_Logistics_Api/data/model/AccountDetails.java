package dev.gagnon.Benue_Produce_Logistics_Api.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "account_details")
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountName;
    @Column(nullable = false)
    private String accountNumber;
    @Column(nullable = false)
    private String bankName;

    @ManyToOne
    private Buyer buyer;
}

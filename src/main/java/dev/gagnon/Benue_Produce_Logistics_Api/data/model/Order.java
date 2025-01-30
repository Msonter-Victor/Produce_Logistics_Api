package dev.gagnon.Benue_Produce_Logistics_Api.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "`_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Buyer buyer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();


    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> product;


}

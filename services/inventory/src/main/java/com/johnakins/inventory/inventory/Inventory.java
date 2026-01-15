package com.johnakins.inventory.inventory;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer productId;

    @Column(nullable = false)
    private Integer availableQuantity;

    @Column(nullable = false)
    private Integer reservedQuantity;

    //protected Inventory() {}

    public Inventory(Integer productId, Integer quantity) {
        this.productId = productId;
        this.availableQuantity = quantity;
        this.reservedQuantity = 0;
    }
}
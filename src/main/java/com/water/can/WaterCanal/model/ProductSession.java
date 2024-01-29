package com.water.can.WaterCanal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

// mark class as an Entity
@Entity
@Data
@NoArgsConstructor
@Table(name = "product_session")
public class ProductSession {

    // Defining book id as primary key
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String productSessionName;

    private String productDec;

    private String originalPrice;

    private String DiscountPrice;

    private String url;

}
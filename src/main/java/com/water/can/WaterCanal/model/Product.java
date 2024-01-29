package com.water.can.WaterCanal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

// mark class as an Entity
@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product{

    // Defining book id as primary key
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String productName;

    private String price;

    private String minimum_order;

    private boolean subscribe;

    private String url;

}
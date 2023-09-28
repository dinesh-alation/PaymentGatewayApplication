package com.example.Payment_gateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String merchantId;
    private String name;
    @Column(unique = true)
    private String email;
    private String businessType;
    private String address;
    private String phone;
}

package com.example.Payment_gateway.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantDTO {
    private Long merchantId;
    private String name;
    private String email;
    private String businessType;
    private String address;
    private String phone;

}

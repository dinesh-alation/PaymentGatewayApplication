package com.example.Payment_gateway.DTOS;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {

    private Long merchantId;
    private BigDecimal amount;
    private String currency;
    private String orderId;
}

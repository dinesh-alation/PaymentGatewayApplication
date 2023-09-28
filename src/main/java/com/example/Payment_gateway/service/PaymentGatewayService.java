package com.example.Payment_gateway.service;

import com.example.Payment_gateway.DTOS.PaymentDTO;
import org.springframework.http.ResponseEntity;

public interface PaymentGatewayService {

     ResponseEntity<?> makePayment(PaymentDTO paymentDTO);
    ResponseEntity<?> checkPayment(String paymentId);
}

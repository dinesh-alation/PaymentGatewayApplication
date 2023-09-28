package com.example.Payment_gateway.service.impl;

import com.example.Payment_gateway.DTOS.PaymentDTO;
import com.example.Payment_gateway.Enum.PaymentStatus;
import com.example.Payment_gateway.Repository.PaymentRepository;
import com.example.Payment_gateway.entity.Payment;
import com.example.Payment_gateway.service.PaymentGatewayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public ResponseEntity<?> makePayment(PaymentDTO paymentDTO) {
        try {
            // Validate paymentDTO

            String paymentCode = generatePaymentCode();
            Payment payment = objectMapper.convertValue(paymentDTO, Payment.class);
            payment.setPaymentCode(paymentCode);
            PaymentStatus paymentStatus;

            if (isPaymentSuccessful(paymentDTO)) {
                payment.setPaymentStatus(PaymentStatus.SUCCESS.toString());
            } else {
                payment.setPaymentStatus(PaymentStatus.FAILURE.toString());
            }
            paymentRepository.save(payment);

            // Return response with payment code
            Map<String, String> response = new HashMap<>();
            response.put("message", "Payment Done successfully");
            response.put("paymentCode", paymentCode);
            response.put("paymentStatus", payment.getPaymentStatus());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Handle exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + e.getMessage());
        }
    }

    private boolean isPaymentSuccessful(PaymentDTO paymentDTO) {
        BigDecimal amount = paymentDTO.getAmount();
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }
    private String generatePaymentCode() {
        return UUID.randomUUID().toString();
    }


    @Override
    public ResponseEntity<?> checkPayment(String paymentId) {

        if (paymentId == null || paymentId.isEmpty()) {
            return new ResponseEntity<>("Payment ID cannot be empty", HttpStatus.BAD_REQUEST);
        }

        Optional<Payment> payment = paymentRepository.findByPaymentCode(paymentId);

        if (payment.isEmpty()) {
            return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
        } else {
            String paymentStatusString = payment.get().getPaymentStatus(); // Assuming getPaymentStatus() returns a String

            if (paymentStatusString == null || paymentStatusString.isEmpty()) {
                return new ResponseEntity<>("Payment status is missing or invalid", HttpStatus.BAD_REQUEST);
            }

            PaymentStatus status;
            try {
                status = PaymentStatus.valueOf(paymentStatusString); // Convert the String to PaymentStatus enum
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>("Invalid payment status", HttpStatus.BAD_REQUEST);
            }

            if (status == PaymentStatus.SUCCESS) {
                return ResponseEntity.ok("Payment was successful");
            } else {
                return ResponseEntity.ok("Payment was unsuccessful");
            }
        }
    }


}


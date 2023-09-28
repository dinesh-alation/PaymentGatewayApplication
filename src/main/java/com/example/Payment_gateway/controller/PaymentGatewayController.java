package com.example.Payment_gateway.controller;

import com.example.Payment_gateway.DTOS.PaymentDTO;
import com.example.Payment_gateway.service.PaymentGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment-gateway")
public class PaymentGatewayController {


    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @PostMapping("/makePayment")
    public ResponseEntity<?> makePayment(@RequestBody PaymentDTO paymentDTO) {
        return new ResponseEntity<>(paymentGatewayService.makePayment(paymentDTO),HttpStatus.OK);    }

    @GetMapping("/status/{paymentCode}")
    public ResponseEntity<?> checkPaymentStatus(@PathVariable String paymentCode) {
        return new ResponseEntity<>(paymentGatewayService.checkPayment(paymentCode), HttpStatus.OK);
    }

}

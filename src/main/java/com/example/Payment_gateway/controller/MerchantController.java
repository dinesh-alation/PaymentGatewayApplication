package com.example.Payment_gateway.controller;

import com.example.Payment_gateway.DTOS.MerchantDTO;
import com.example.Payment_gateway.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantservice;

    @PostMapping("/register")
    public ResponseEntity<?> registerMerchant(@RequestBody MerchantDTO merchantDTO) {
        return new ResponseEntity<>(merchantservice.registerMerchant(merchantDTO), HttpStatus.OK);
    }


}

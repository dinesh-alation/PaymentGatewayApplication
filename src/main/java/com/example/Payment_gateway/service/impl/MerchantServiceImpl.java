package com.example.Payment_gateway.service.impl;

import com.example.Payment_gateway.DTOS.MerchantDTO;
import com.example.Payment_gateway.Repository.MerchantRepository;
import com.example.Payment_gateway.entity.Merchant;
import com.example.Payment_gateway.service.MerchantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    ObjectMapper objectMapper;

        @Override
        public ResponseEntity<?> registerMerchant(MerchantDTO merchantDTO) {
            try {
                // Map MerchantDTO to Merchant entity using ObjectMapper
                Merchant merchant = objectMapper.convertValue(merchantDTO, Merchant.class);

                // Save the merchant to the database
                merchantRepository.save(merchant);

                // Return a success response
                return ResponseEntity.ok("Merchant registered successfully.");
            } catch (Exception e) {
                // Handle exceptions
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Merchant registration failed. Please try again later.");
            }
        }
    }






package com.example.Payment_gateway.service;

import com.example.Payment_gateway.DTOS.MerchantDTO;
import org.springframework.http.ResponseEntity;

public interface MerchantService {

    ResponseEntity<?> registerMerchant( MerchantDTO merchantDTO);

}

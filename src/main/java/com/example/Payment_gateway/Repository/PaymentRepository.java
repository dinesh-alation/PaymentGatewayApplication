package com.example.Payment_gateway.Repository;

import com.example.Payment_gateway.DTOS.PaymentDTO;
import com.example.Payment_gateway.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByPaymentCode(String paymentCode);
}

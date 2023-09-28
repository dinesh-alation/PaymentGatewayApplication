package com.example.Payment_gateway.Repository;

import com.example.Payment_gateway.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}

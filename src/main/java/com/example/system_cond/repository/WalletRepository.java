package com.example.system_cond.repository;

import com.example.system_cond.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,String> {
    Optional<Wallet> findById(Long walletId);
}

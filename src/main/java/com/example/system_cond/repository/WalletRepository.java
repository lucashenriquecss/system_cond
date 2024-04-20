package com.example.system_cond.repository;

import com.example.system_cond.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet,String> {
}

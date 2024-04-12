package com.example.system_cond.repository;

import com.example.system_cond.entity.Balance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,String> {
}

package com.example.system_cond.repository;

import com.example.system_cond.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentRepository extends JpaRepository<Resident, String> {
}

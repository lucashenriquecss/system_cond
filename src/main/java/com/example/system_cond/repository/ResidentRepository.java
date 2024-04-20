package com.example.system_cond.repository;

import com.example.system_cond.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident,String> {
    Optional<Object> findById(Long residentId);
}

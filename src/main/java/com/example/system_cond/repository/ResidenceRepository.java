package com.example.system_cond.repository;

import com.example.system_cond.entity.Residence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResidenceRepository extends JpaRepository<Residence,String> {
    Optional<Object> findById(Long residenceId);
}

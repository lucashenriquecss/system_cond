package com.example.system_cond.repository;

import com.example.system_cond.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,String> {
    Optional<Booking> findById(Long bookingId);
}

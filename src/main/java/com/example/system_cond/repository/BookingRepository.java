package com.example.system_cond.repository;

import com.example.system_cond.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,String> {
}

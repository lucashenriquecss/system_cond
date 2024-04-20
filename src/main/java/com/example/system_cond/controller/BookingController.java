package com.example.system_cond.controller;

import com.example.system_cond.dto.BookingDTO;
import com.example.system_cond.entity.Booking;
import com.example.system_cond.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookingDTOs = bookingService.getAllBookings().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookingDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable String id) {
        BookingDTO bookingDTO = convertToDTO(bookingService.getBookingById(id));
        if (bookingDTO != null) {
            return ResponseEntity.ok(bookingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO createdBookingDTO = convertToDTO(bookingService.createBooking(bookingDTO));
        return new ResponseEntity<>(createdBookingDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBooking(@PathVariable String id, @RequestBody BookingDTO bookingDTO) {
        BookingDTO updatedBookingDTO = convertToDTO(bookingService.updateBooking(id, bookingDTO));
        if (updatedBookingDTO != null) {
            return ResponseEntity.ok(updatedBookingDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

    private BookingDTO convertToDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setDescription(booking.getDescription());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setBookingDate(booking.getBookingDate());
        bookingDTO.setResident(booking.getResident());
        // Outros campos, se necessário
        return bookingDTO;
    }
}

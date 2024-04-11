package com.example.system_cond.controller;

import com.example.system_cond.dto.ReserveDTO;
import com.example.system_cond.entity.Reserve;
import com.example.system_cond.service.ReserveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reserve")
public class ReserveController {
    private final ReserveService reserveService;
    
    public ReserveController(ReserveService reserveService){
        this.reserveService = reserveService;
    }
    @GetMapping
    public ResponseEntity<List<ReserveDTO>> getAllReserves() {
        List<ReserveDTO> reserve = reserveService.getAllReserves().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reserve);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveDTO> getReserveById(@PathVariable String id) {
        ReserveDTO reserve = reserveService.getReserveById(id);
        return ResponseEntity.ok(reserve);
    }

    @PostMapping
    public ResponseEntity<ReserveDTO> createReserve(@RequestBody ReserveDTO reserveDTO) {
        ReserveDTO createReserve = reserveService.createReserve(reserveDTO);
        return ResponseEntity.ok(createReserve);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReserveDTO> updateReserve(@PathVariable String id, @RequestBody ReserveDTO reserveDTO) {
        ReserveDTO updatedReserve = reserveService.updateReserve(id, reserveDTO);
        return ResponseEntity.ok(updatedReserve);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserve(@PathVariable String id) {
        reserveService.deleteReserve(id);
        return ResponseEntity.noContent().build();
    }

    private ReserveDTO convertToDTO(Reserve reserve) {
        ReserveDTO reserveDTO = new ReserveDTO();

        reserveDTO.setName(reserve.getName());
        reserveDTO.setDescription(reserve.getDescription());
        reserveDTO.setAvailability(reserve.getAvailability());
        reserveDTO.setReserveDate(reserve.getReserveDate());

        return reserveDTO;
    }
}

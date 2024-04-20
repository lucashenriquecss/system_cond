package com.example.system_cond.controller;
import com.example.system_cond.dto.ResidenceDTO;
import com.example.system_cond.entity.Residence;
import com.example.system_cond.service.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/residence")
public class ResidenceController {

    private final ResidenceService residenceService;

    @Autowired
    public ResidenceController(ResidenceService residenceService) {
        this.residenceService = residenceService;
    }

    @GetMapping
    public ResponseEntity<List<ResidenceDTO>> getAllResidences() {
        List<ResidenceDTO> residenceDTOs = residenceService.getAllResidences().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(residenceDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidenceDTO> getResidenceById(@PathVariable String id) {
        ResidenceDTO residenceDTO = convertToDTO(residenceService.getResidenceById(id));
        if (residenceDTO != null) {
            return ResponseEntity.ok(residenceDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ResidenceDTO> createResidence(@RequestBody ResidenceDTO residenceDTO) {
        ResidenceDTO createdResidenceDTO = convertToDTO(residenceService.createResidence(residenceDTO));
        return new ResponseEntity<>(createdResidenceDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidenceDTO> updateResidence(@PathVariable String id, @RequestBody ResidenceDTO residenceDTO) {
        ResidenceDTO updatedResidenceDTO = convertToDTO(residenceService.updateResidence(id, residenceDTO));
        if (updatedResidenceDTO != null) {
            return ResponseEntity.ok(updatedResidenceDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResidence(@PathVariable String id) {
        residenceService.deleteResidence(id);
        return ResponseEntity.noContent().build();
    }

    private ResidenceDTO convertToDTO(Residence residence) {
        ResidenceDTO residenceDTO = new ResidenceDTO();
        residenceDTO.setId(residence.getId());
        residenceDTO.setNumber(residence.getNumber());
        residenceDTO.setRoomNumber(residence.getRoomNumber());
        residenceDTO.setResidents(residence.getResidents());

        // Outros campos, se necessário
        return residenceDTO;
    }
}

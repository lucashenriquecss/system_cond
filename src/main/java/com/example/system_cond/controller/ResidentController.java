package com.example.system_cond.controller;

import com.example.system_cond.dto.ResidentDTO;
import com.example.system_cond.entity.Resident;
import com.example.system_cond.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/residents")
public class ResidentController {

    private final ResidentService residentService;

    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public ResponseEntity<List<ResidentDTO>> getAllResidents() {
        List<Resident> residents = residentService.getAllResidents();
        List<ResidentDTO> residentDTOs = residents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(residentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentDTO> getResidentById(@PathVariable String id) {
        Resident resident = residentService.getResidentById(id);
        if (resident != null) {
            return ResponseEntity.ok(convertToDTO(resident));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ResidentDTO> createResident(@RequestBody ResidentDTO residentDTO) {
        Resident resident = residentService.createResident(convertToEntity(residentDTO));
        return new ResponseEntity<>(convertToDTO(resident), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidentDTO> updateResident(@PathVariable String id, @RequestBody ResidentDTO residentDTO) {
        Resident resident = residentService.updateResident(id, convertToEntity(residentDTO));
        if (resident != null) {
            return ResponseEntity.ok(convertToDTO(resident));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResident(@PathVariable String id) {
        residentService.deleteResident(id);
        return ResponseEntity.noContent().build();
    }

    private ResidentDTO convertToDTO(Resident resident) {
        ResidentDTO residentDTO = new ResidentDTO();
        residentDTO.setId(resident.getId());
        residentDTO.setName(resident.getName());
        residentDTO.setContact(resident.getContact());
        residentDTO.setEmail(resident.getEmail());
        // Se necessário, mapeie outros campos
        return residentDTO;
    }

    private Resident convertToEntity(ResidentDTO residentDTO) {
        Resident resident = new Resident();
        resident.setId(residentDTO.getId());
        resident.setName(residentDTO.getName());
        resident.setContact(residentDTO.getContact());
        resident.setEmail(residentDTO.getEmail());
        // Se necessário, mapeie outros campos
        return resident;
    }
}

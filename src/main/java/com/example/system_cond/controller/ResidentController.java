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
@RequestMapping("/api/resident")
public class ResidentController {
    private final ResidentService residentService;

    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public ResponseEntity<List<ResidentDTO>> getAllResidents() {
        List<ResidentDTO> residentDTOs = residentService.getAllResidents().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(residentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResidentDTO> getResidentById(@PathVariable String id) {
        ResidentDTO residentDTO = convertToDTO(residentService.getResidentById(id));
        if (residentDTO != null) {
            return ResponseEntity.ok(residentDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ResidentDTO> createResident(@RequestBody ResidentDTO residentDTO) {
        ResidentDTO createdResidentDTO = convertToDTO(residentService.createResident(residentDTO));
        return new ResponseEntity<>(createdResidentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResidentDTO> updateResident(@PathVariable String id, @RequestBody ResidentDTO residentDTO) {
        ResidentDTO updatedResidentDTO = convertToDTO(residentService.updateResident(id, residentDTO));
        if (updatedResidentDTO != null) {
            return ResponseEntity.ok(updatedResidentDTO);
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
        residentDTO.setName(resident.getName());
        residentDTO.setContact(resident.getContact());
        residentDTO.setEmail(resident.getEmail());

        return residentDTO;
    }
}

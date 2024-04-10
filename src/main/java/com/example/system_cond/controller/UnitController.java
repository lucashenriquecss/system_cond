package com.example.system_cond.controller;

import com.example.system_cond.dto.UnitDTO;
import com.example.system_cond.entity.Unit;
import com.example.system_cond.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/units")
public class UnitController {

    private final UnitService unitService;

    @Autowired
    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public ResponseEntity<List<UnitDTO>> getAllUnits() {
        List<UnitDTO> unitDTOs = unitService.getAllUnits().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(unitDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitDTO> getUnitById(@PathVariable String id) {
        UnitDTO unitDTO = convertToDTO(unitService.getUnitById(id));
        if (unitDTO != null) {
            return ResponseEntity.ok(unitDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UnitDTO> createUnit(@RequestBody UnitDTO unitDTO) {
        UnitDTO createdUnitDTO = convertToDTO(unitService.createUnit(unitDTO));
        return new ResponseEntity<>(createdUnitDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitDTO> updateUnit(@PathVariable String id, @RequestBody UnitDTO unitDTO) {
        UnitDTO updatedUnitDTO = convertToDTO(unitService.updateUnit(id, unitDTO));
        if (updatedUnitDTO != null) {
            return ResponseEntity.ok(updatedUnitDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable String id) {
        unitService.deleteUnit(id);
        return ResponseEntity.noContent().build();
    }

    private UnitDTO convertToDTO(Unit unit) {
        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(unit.getId());
        unitDTO.setNumber(unit.getNumber());
        unitDTO.setRoomNumber(unit.getRoomNumber());
        unitDTO.setResidents(unit.getResidents());

        // Outros campos, se necessário
        return unitDTO;
    }
}

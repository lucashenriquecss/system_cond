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
        List<Unit> units = unitService.getAllUnits();
        List<UnitDTO> unitDTOs = units.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(unitDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitDTO> getUnitById(@PathVariable String id) {
        Unit unit = unitService.getUnitById(id);
        if (unit != null) {
            return ResponseEntity.ok(convertToDTO(unit));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UnitDTO> createUnit(@RequestBody UnitDTO unitDTO) {
        Unit unit = unitService.createUnit(convertToEntity(unitDTO));
        return new ResponseEntity<>(convertToDTO(unit), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitDTO> updateUnit(@PathVariable String id, @RequestBody UnitDTO unitDTO) {
        Unit unit = unitService.updateUnit(id, convertToEntity(unitDTO));
        if (unit != null) {
            return ResponseEntity.ok(convertToDTO(unit));
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
        // Se necessário, mapeie outros campos
        return unitDTO;
    }

    private Unit convertToEntity(UnitDTO unitDTO) {
        Unit unit = new Unit();
        unit.setId(unitDTO.getId());
        unit.setNumber(unitDTO.getNumber());
        unit.setRoomNumber(unitDTO.getRoomNumber());
        // Se necessário, mapeie outros campos
        return unit;
    }
}

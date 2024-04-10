package com.example.system_cond.service;

import com.example.system_cond.dto.UnitDTO;

import com.example.system_cond.entity.Unit;

import com.example.system_cond.repository.UnitRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public List<UnitDTO> getAllUnits() {
        return unitRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UnitDTO getUnitById(String id) {
        Optional<Unit> unitOptional = unitRepository.findById(id);
        return unitOptional.map(this::convertToDTO).orElse(null);
    }

    public UnitDTO createUnit(UnitDTO unitDTO) {
        Unit unit = convertToEntity(unitDTO);
        Unit savedUnit = unitRepository.save(unit);
        return convertToDTO(savedUnit);
    }

    public UnitDTO updateUnit(String id, UnitDTO unitDTO) {
        Optional<Unit> unitOptional = unitRepository.findById(id);
        if (unitOptional.isPresent()) {
            Unit unit = unitOptional.get();
            // Atualize os campos da unidade com base nos dados do DTO
            unit.setNumber(unitDTO.getNumber());
            unit.setRoomNumber(unitDTO.getRoomNumber());
            // Salve a unidade atualizada
            Unit updatedUnit = unitRepository.save(unit);
            return convertToDTO(updatedUnit);
        }
        return null;
    }

    public void deleteUnit(String id) {
        unitRepository.deleteById(id);
    }

    private UnitDTO convertToDTO(Unit unit) {
        UnitDTO unitDTO = new UnitDTO();
        unitDTO.setId(unit.getId());
        unitDTO.setNumber(unit.getNumber());
        unitDTO.setRoomNumber(unit.getRoomNumber());
        unitDTO.setResidents(unit.getResidents());
        return unitDTO;
    }

    private Unit convertToEntity(UnitDTO unitDTO) {
        Unit unit = new Unit();
        // Converta os campos do DTO para a entidade Unit
        unit.setNumber(unitDTO.getNumber());
        unit.setRoomNumber(unitDTO.getRoomNumber());
        return unit;
    }
}

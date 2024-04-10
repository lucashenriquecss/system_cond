package com.example.system_cond.service;

import com.example.system_cond.dto.ResidentDTO;
import com.example.system_cond.entity.Resident;
import com.example.system_cond.entity.Unit;
import com.example.system_cond.repository.ResidentRepository;
import com.example.system_cond.repository.UnitRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final UnitRepository unitRepository;

    public ResidentService(ResidentRepository residentRepository, UnitRepository unitRepository) {
        this.residentRepository = residentRepository;
        this.unitRepository = unitRepository;
    }

    public List<ResidentDTO> getAllResidents() {
        return residentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResidentDTO getResidentById(String id) {
        Optional<Resident> residentOptional = residentRepository.findById(id);
        return residentOptional.map(this::convertToDTO).orElse(null);
    }

    public ResidentDTO createResident(ResidentDTO residentDTO) {
        Resident resident = convertToEntity(residentDTO);
        Resident savedResident = residentRepository.save(resident);
        return convertToDTO(savedResident);
    }

    public ResidentDTO updateResident(String id, ResidentDTO residentDTO) {
        Optional<Resident> residentOptional = residentRepository.findById(id);
        if (residentOptional.isPresent()) {
            Resident resident = residentOptional.get();
            // Atualize os campos do residente com base nos dados do DTO
            resident.setName(residentDTO.getName());
            resident.setContact(residentDTO.getContact());
            resident.setEmail(residentDTO.getEmail());
            // Salve o residente atualizado
            Resident updatedResident = residentRepository.save(resident);
            return convertToDTO(updatedResident);
        }
        return null;
    }

    public void deleteResident(String id) {
        residentRepository.deleteById(id);
    }

    private ResidentDTO convertToDTO(Resident resident) {
        ResidentDTO residentDTO = new ResidentDTO();
        residentDTO.setName(resident.getName());
        residentDTO.setContact(resident.getContact());
        residentDTO.setEmail(resident.getEmail());
        residentDTO.setUnitId(String.valueOf(resident.getUnit()));
        return residentDTO;
    }

    private Resident convertToEntity(ResidentDTO residentDTO) {
        Resident resident = new Resident();
        resident.setName(residentDTO.getName());
        resident.setContact(residentDTO.getContact());
        resident.setEmail(residentDTO.getEmail());
        Unit unit = unitRepository.findById(residentDTO.getUnitId()).orElse(null);
        resident.setUnit(unit);

        return resident;
    }
}

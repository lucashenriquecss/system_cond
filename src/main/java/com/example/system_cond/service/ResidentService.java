package com.example.system_cond.service;

import com.example.system_cond.dto.ResidentDTO;
import com.example.system_cond.entity.Residence;
import com.example.system_cond.entity.Resident;
import com.example.system_cond.repository.ResidenceRepository;
import com.example.system_cond.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResidentService {
    private final ResidentRepository residentRepository;
    private final ResidenceRepository residenceRepository;

    public ResidentService(ResidentRepository residentRepository, ResidenceRepository residenceRepository) {
        this.residentRepository = residentRepository;
        this.residenceRepository = residenceRepository;
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

            resident.setName(residentDTO.getName());
            resident.setContact(residentDTO.getContact());
            resident.setEmail(residentDTO.getEmail());
            
            Resident updatedResident = residentRepository.save(resident);
            return convertToDTO(updatedResident);
        }
        return null;
    }

    public void deleteResident(String id) {
        residentRepository.deleteById(id);
    }

    private ResidentDTO convertToDTO(Resident resident) {
        ResidentDTO residentDTO = new ResidentDTO(); //adicionar ID eo restante no retorno

        residentDTO.setName(resident.getName());
        residentDTO.setContact(resident.getContact());
        residentDTO.setEmail(resident.getEmail());
        residentDTO.setResidenceId(resident.getResidence().getId());
        return residentDTO;
    }

    private Resident convertToEntity(ResidentDTO residentDTO) {
        Resident resident = new Resident();
        resident.setName(residentDTO.getName());
        resident.setContact(residentDTO.getContact());
        resident.setEmail(residentDTO.getEmail());
        Residence residence = (Residence) residenceRepository.findById(residentDTO.getResidenceId()).orElse(null);
        resident.setResidence(residence);

        return resident;
    }
}

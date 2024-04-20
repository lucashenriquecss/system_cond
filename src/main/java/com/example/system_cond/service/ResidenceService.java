package com.example.system_cond.service;

import com.example.system_cond.dto.ResidenceDTO;
import com.example.system_cond.entity.Residence;
import com.example.system_cond.repository.ResidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResidenceService {
    private final ResidenceRepository residenceRepository;

    public ResidenceService(ResidenceRepository residenceRepository) {
        this.residenceRepository = residenceRepository;
    }

    public List<ResidenceDTO> getAllResidences() {
        return residenceRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResidenceDTO getResidenceById(String id) {
        Optional<Residence> residenceOptional = residenceRepository.findById(id);
        return residenceOptional.map(this::convertToDTO).orElse(null);
    }

    public ResidenceDTO createResidence(ResidenceDTO residenceDTO) {
        Residence residence = convertToEntity(residenceDTO);
        Residence savedResidence = residenceRepository.save(residence);
        return convertToDTO(savedResidence);
    }

    public ResidenceDTO updateResidence(String id, ResidenceDTO residenceDTO) {
        Optional<Residence> residenceOptional = residenceRepository.findById(id);
        if (residenceOptional.isPresent()) {
            Residence residence = residenceOptional.get();
            residence.setNumber(residenceDTO.getNumber());
            residence.setRoomNumber(residenceDTO.getRoomNumber());
            Residence updatedResidence = residenceRepository.save(residence);
            return convertToDTO(updatedResidence);
        }
        return null;
    }

    public void deleteResidence(String id) {
        residenceRepository.deleteById(id);
    }

    private ResidenceDTO convertToDTO(Residence residence) {
        ResidenceDTO residenceDTO = new ResidenceDTO();
        residenceDTO.setId(residence.getId());
        residenceDTO.setNumber(residence.getNumber());
        residenceDTO.setRoomNumber(residence.getRoomNumber());
        residenceDTO.setResidents(residence.getResidents());
        return residenceDTO;
    }

    private Residence convertToEntity(ResidenceDTO residenceDTO) {
        Residence residence = new Residence();
        residence.setNumber(residenceDTO.getNumber());
        residence.setRoomNumber(residenceDTO.getRoomNumber());
        return residence;
    }
}

package com.example.system_cond.service;

import com.example.system_cond.dto.ReserveDTO;
import com.example.system_cond.entity.Reserve;
import com.example.system_cond.entity.Resident;

import com.example.system_cond.repository.ReserveRepository;
import com.example.system_cond.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final ResidentRepository residentRepository;

    public ReserveService(ReserveRepository reserveRepository,ResidentRepository residentRepository) {
        this.reserveRepository = reserveRepository;
        this.residentRepository = residentRepository;
    }

    public List<ReserveDTO> getAllReserves() {
        return reserveRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReserveDTO getReserveById(String id) {
        Optional<Reserve> reserveOptional = reserveRepository.findById(id);
        return reserveOptional.map(this::convertToDTO).orElse(null);
    }

    public ReserveDTO createReserve(ReserveDTO reserveDTO) {
        Reserve reserve = convertToEntity(reserveDTO);
        Reserve savedReserve = reserveRepository.save(reserve);
        return convertToDTO(savedReserve);
    }

    public ReserveDTO updateReserve(String id, ReserveDTO reserveDTO) {
        Optional<Reserve> reserveOptional = reserveRepository.findById(id);
        if (reserveOptional.isPresent()) {
            Reserve reserve = reserveOptional.get();

            reserve.setName(reserveDTO.getName());
            reserve.setDescription(reserveDTO.getDescription());
            reserve.setAvailability(reserveDTO.getAvailability());
            reserve.setReserveDate(reserveDTO.getReserveDate());

            Reserve updateReserve = reserveRepository.save(reserve);
            return convertToDTO(updateReserve);
        }
        return null;
    }

    public void deleteReserve(String id) {
        reserveRepository.deleteById(id);
    }

    private ReserveDTO convertToDTO(Reserve reserve) {
        ReserveDTO reserveDTO = new ReserveDTO();

        reserveDTO.setId(reserve.getId());
        reserveDTO.setName(reserve.getName());
        reserveDTO.setDescription(reserve.getDescription());
        reserveDTO.setAvailability(reserve.getAvailability());
        reserveDTO.setReserveDate(reserve.getReserveDate());

        reserveDTO.setResidentId(String.valueOf(reserve.getResident()));
        return reserveDTO;
    }

    private Reserve convertToEntity(ReserveDTO reserveDTO) {
        Reserve reserve = new Reserve();

        reserve.setName(reserveDTO.getName());
        reserve.setDescription(reserveDTO.getDescription());
        reserve.setAvailability(reserveDTO.getAvailability());
        reserve.setReserveDate(reserveDTO.getReserveDate());

        Resident resident = residentRepository.findById(reserveDTO.getResidentId()).orElse(null);
        reserve.setResident(resident);
        return reserve;
    }
}

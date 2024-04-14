package com.example.system_cond.service;

import com.example.system_cond.dto.ResidentDTO;
import com.example.system_cond.dto.SpentDTO;

import com.example.system_cond.entity.Balance;
import com.example.system_cond.entity.Resident;
import com.example.system_cond.entity.Spent;
import com.example.system_cond.entity.Unit;
import com.example.system_cond.repository.BalanceRepository;
import com.example.system_cond.repository.SpentRepository;
import org.springframework.stereotype.Service;

@Service
public class SpentService {
    private final SpentRepository spentRepository;
    private final BalanceRepository balanceRepository;

    public SpentService(SpentRepository spentRepository,BalanceRepository balanceRepository) {
        this.spentRepository = spentRepository;
        this.balanceRepository = balanceRepository;
    }

    public SpentDTO createSpent(SpentDTO spentDTO) {
        Spent spent = new Spent();

        spent= convertToEntity(spentDTO);

        spent = spentRepository.save(spent);

        updateBalance(spentDTO);

        return convertToDTO(spent);
    }

    private void updateBalance(SpentDTO spentDTO) {
        Balance balance = null;

        if (spentDTO.getBalanceId() != null) {
            balance = balanceRepository.findById(spentDTO.getBalanceId()).orElse(null);
        }

        balance.registerSpent(spentDTO.getValue());


        Balance updatedBalance = balanceRepository.save(balance);


    }
    private SpentDTO convertToDTO(Spent spent) {
        SpentDTO spentDTO = new SpentDTO();
        spentDTO.setId(spent.getId());
        spentDTO.setValue(spent.getValue());
        spentDTO.setDate(spent.getDate());
        spentDTO.setType(spent.getType());
        spent.setDescription(spent.getDescription());

        return spentDTO;
    }
    private Spent convertToEntity(SpentDTO spentDTO) {
        Spent spent = new Spent();
        spent.setValue(spentDTO.getValue());
        spent.setDate(spentDTO.getDate());
        spent.setType(spentDTO.getType());
        spent.setDescription(spentDTO.getDescription());
        spent.setBalanceId(spentDTO.getBalanceId());


        return spent;
    }
}
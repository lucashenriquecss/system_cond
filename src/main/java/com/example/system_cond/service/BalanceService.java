package com.example.system_cond.service;

import com.example.system_cond.dto.BalanceDTO;
import com.example.system_cond.dto.PaymentDTO;
import com.example.system_cond.entity.Balance;
import com.example.system_cond.repository.BalanceRepository;
import org.springframework.stereotype.Service;



@Service
public class BalanceService {
    private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository){
        this.balanceRepository = balanceRepository;
    }
    public BalanceDTO updateBalance(PaymentDTO paymentDTO) {
        Balance balance = null;

        if (paymentDTO.getBalanceId() != null) {
            balance = balanceRepository.findById(paymentDTO.getBalanceId()).orElse(null);
        }

        if (balance == null) {
            balance = new Balance();
            balance.setBalanceAvailable(paymentDTO.getValue());
        } else {
            balance.registerPayment(paymentDTO.getValue());
        }

        Balance updatedBalance = balanceRepository.save(balance);

        return convertToDTO(updatedBalance);
    }

    private BalanceDTO convertToDTO(Balance balance) {
        BalanceDTO balanceDTO = new BalanceDTO();

        balanceDTO.setId(balance.getId());
        balanceDTO.setBalanceAvailable(balance.getBalanceAvailable());

        return balanceDTO;
    }
}

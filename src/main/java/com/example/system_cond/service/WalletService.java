package com.example.system_cond.service;

import com.example.system_cond.dto.ResidentDTO;
import com.example.system_cond.dto.WalletDTO;
import com.example.system_cond.entity.Wallet;
import com.example.system_cond.repository.BookingRepository;
import com.example.system_cond.repository.ResidenceRepository;
import com.example.system_cond.repository.ResidentRepository;
import com.example.system_cond.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {

        this.walletRepository = walletRepository;
    }
    public List<WalletDTO> getAllWallet() {
        return walletRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private WalletDTO convertToDTO(Wallet wallet) {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(wallet.getId());
        walletDTO.setWallet(wallet.getWallet());
        walletDTO.setTransactions(wallet.getTransactions());

        return walletDTO;
    }
}

package com.example.system_cond.controller;
import com.example.system_cond.dto.WalletDTO;
import com.example.system_cond.dto.WalletDTO;
import com.example.system_cond.entity.Payment;
import com.example.system_cond.entity.Wallet;
import com.example.system_cond.service.PaymentService;
import com.example.system_cond.service.TransactionService;
import com.example.system_cond.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final WalletService walletService;
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }
    @GetMapping
    public ResponseEntity<List<WalletDTO>> getAllPayments() {
        List<WalletDTO> payments = walletService.getAllWallet().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());;
        return ResponseEntity.ok(payments);
    }

    private WalletDTO convertToDTO(Wallet wallet) {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(wallet.getId());
        walletDTO.setWallet(wallet.getWallet());
        walletDTO.setTransactions(wallet.getTransactions());

        return walletDTO;
    }
}

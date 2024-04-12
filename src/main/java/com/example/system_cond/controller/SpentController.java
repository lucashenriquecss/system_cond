package com.example.system_cond.controller;

import com.example.system_cond.dto.PaymentDTO;
import com.example.system_cond.dto.SpentDTO;
import com.example.system_cond.service.BalanceService;
import com.example.system_cond.service.PaymentService;
import com.example.system_cond.service.SpentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spent")
public class SpentController {
    private final PaymentService paymentService;
    private final BalanceService balanceService;
    private final SpentService spentService;

    public SpentController( SpentService spentService,PaymentService paymentService,BalanceService balanceService) {
        this.paymentService = paymentService;
        this.balanceService = balanceService;
        this.spentService = spentService;
    }

    @PostMapping
    public ResponseEntity<SpentDTO> createPayment(@RequestBody SpentDTO spentDTO) {
        SpentDTO createdSpent = spentService.createSpent(spentDTO);
        return ResponseEntity.ok(createdSpent);
    }
}

package com.example.system_cond.controller;

import com.example.system_cond.dto.ExtractDTO;
import com.example.system_cond.dto.PaymentDTO;
import com.example.system_cond.entity.Payment;
import com.example.system_cond.service.BalanceService;
import com.example.system_cond.service.ExtractService;
import com.example.system_cond.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final BalanceService balanceService;
    private final ExtractService extractService;
    public PaymentController(ExtractService extractService,PaymentService paymentService,BalanceService balanceService) {
        this.paymentService = paymentService;
        this.balanceService = balanceService;
        this.extractService = extractService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        List<PaymentDTO> payments = paymentService.getAllPayments().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());;
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable String id) {
        PaymentDTO payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);
        return ResponseEntity.ok(createdPayment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable String id, @RequestBody PaymentDTO paymentDTO) {
        PaymentDTO updatedPayment = paymentService.updatePayment(id, paymentDTO);

        balanceService.updateBalance(paymentDTO);
        extractService.createExtractPayment(paymentDTO);

        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable String id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setValue(payment.getValue());
        paymentDTO.setDueDate(payment.getDueDate());
        paymentDTO.setStatus(payment.getStatus());

        return paymentDTO;
    }
}

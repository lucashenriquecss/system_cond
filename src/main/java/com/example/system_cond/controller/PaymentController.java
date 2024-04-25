package com.example.system_cond.controller;

import org.springframework.web.bind.annotation.*;
import com.example.system_cond.entity.*;
import com.example.system_cond.dto.*;
import com.example.system_cond.service.*;
import org.springframework.http.ResponseEntity;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;
    private final TransactionService transactionService;
    public PaymentController(TransactionService transactionService,PaymentService paymentService) {
        this.paymentService = paymentService;
        this.transactionService = transactionService;
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
        TransactionDTO transactionDTO = convertDTOTransaction(paymentDTO);
        transactionService.createTransaction(transactionDTO);
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
    private TransactionDTO convertDTOTransaction(PaymentDTO paymentDTO){
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setValue(paymentDTO.getValue());
        transactionDTO.setType("payment");
        transactionDTO.setPayer(String.valueOf(paymentDTO.getResidentId()));
        transactionDTO.setPayee("Condominio");
        transactionDTO.setWalletId(Long.valueOf(1));
        transactionDTO.setDescription("Pagamento mensal do condominio");
        return transactionDTO;
    }
}

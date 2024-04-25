package com.example.system_cond.controller;
import com.example.system_cond.dto.PaymentDTO;
import com.example.system_cond.dto.TransactionDTO;
import com.example.system_cond.service.PaymentService;
import com.example.system_cond.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService) {

        this.transactionService = transactionService;
    }
    @PostMapping
    public ResponseEntity<TransactionDTO> createTransactio(@RequestBody TransactionDTO transactionDTO) {
        TransactionDTO createTransaction = transactionService.createTransaction(transactionDTO);

        return ResponseEntity.ok(createTransaction);
    }
}

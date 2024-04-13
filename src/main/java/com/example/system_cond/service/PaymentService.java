package com.example.system_cond.service;

import com.example.system_cond.dto.PaymentDTO;
import com.example.system_cond.dto.UnitDTO;
import com.example.system_cond.entity.Payment;
import com.example.system_cond.entity.Resident;
import com.example.system_cond.entity.Unit;
import com.example.system_cond.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final ResidentRepository residentRepository;
    private final BalanceRepository balanceRepository;
    private final ExtractRepository extractRepository;
    public PaymentService(ExtractRepository extractRepository,PaymentRepository paymentRepository,ResidentRepository residentRepository,BalanceRepository balanceRepository) {
        this.paymentRepository = paymentRepository;
        this.residentRepository = residentRepository;
        this.balanceRepository = balanceRepository;
        this.extractRepository = extractRepository;
    }

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO getPaymentById(String id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        return paymentOptional.map(this::convertToDTO).orElse(null);
    }

    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);

        return convertToDTO(savedPayment);
    }

    public PaymentDTO updatePayment(String id, PaymentDTO paymentDTO) {
        Optional<Payment> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            payment.setValue(paymentDTO.getValue());
            payment.setDueDate(paymentDTO.getDueDate());
            payment.setStatus(paymentDTO.getStatus());

            Payment updatedPayment = paymentRepository.save(payment);
            return convertToDTO(updatedPayment);
        }
        return null;
    }

    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setValue(payment.getValue());
        paymentDTO.setDueDate(payment.getDueDate());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setResidentId(String.valueOf(payment.getResident()));
        return paymentDTO;
    }

    private Payment convertToEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        // Converta os campos do DTO para a entidade Payment
        payment.setValue(paymentDTO.getValue());
        payment.setDueDate(paymentDTO.getDueDate());
        payment.setStatus(paymentDTO.getStatus());
        Resident resident = residentRepository.findById(paymentDTO.getResidentId()).orElse(null);
        payment.setResident(resident);
        return payment;
    }
}
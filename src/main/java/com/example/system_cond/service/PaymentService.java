package com.example.system_cond.service;

import com.example.system_cond.dto.PaymentDTO;
import com.example.system_cond.entity.Payment;
import com.example.system_cond.entity.Resident;
import com.example.system_cond.repository.PaymentRepository;
import com.example.system_cond.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ResidentRepository residentRepository;

    public PaymentService(PaymentRepository paymentRepository, ResidentRepository residentRepository) {
        this.paymentRepository = paymentRepository;
        this.residentRepository = residentRepository;

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
        paymentDTO.setResidentId(payment.getResident().getId());
        return paymentDTO;
    }

    private Payment convertToEntity(PaymentDTO paymentDTO) {
        Payment payment = new Payment();

        payment.setValue(paymentDTO.getValue());
        payment.setDueDate(paymentDTO.getDueDate());
        payment.setStatus(paymentDTO.getStatus());
        Resident resident = (Resident) residentRepository.findById(paymentDTO.getResidentId()).orElse(null);
        payment.setResident(resident);
        return payment;
    }
}

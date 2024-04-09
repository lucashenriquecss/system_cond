package com.example.system_cond.service;

import com.example.system_cond.entity.Payment;
import com.example.system_cond.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(String id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        // Adicione qualquer lógica de validação ou processamento aqui, se necessário
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(String id, Payment updatedPayment) {
        Payment payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            // Atualize os campos do pagamento existente com os valores do pagamento atualizado
            // Certifique-se de que a entidade de pagamento passada está corretamente configurada com o ID atualizado, se necessário
            // Adicione qualquer outra lógica de validação ou processamento aqui, se necessário
            return paymentRepository.save(updatedPayment);
        }
        return null; // ou lance uma exceção indicando que o pagamento não foi encontrado
    }

    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }
}

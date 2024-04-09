package com.example.system_cond.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private String id;
    private Double value;
    private String dueDate;
    private String status;
    private String residentId;
    // Outros campos, se necessário
}

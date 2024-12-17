package com.example.dto;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDto {
    private String id;
    private String paymentMethod;
    private String status;
    private Long bookingId;
    private BigDecimal amount;
    private String transaction_id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
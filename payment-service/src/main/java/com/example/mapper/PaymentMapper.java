package com.example.mapper;

import com.example.dto.PaymentDto;
import com.example.model.Payment;
import com.example.request.PaymentRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentMapper {
    public Payment mapTo(PaymentRequest request)
     {
        String status = "pending";
        if (Math.random() > 0.5) {
            status = "paid";
        } else {
            status = "completed";
        }
        String random = UUID.randomUUID().toString();
        return Payment.builder().transaction_id(random).bookingId(request.getBookingId()).userId(request.getUserId()).payment_method(request.getPaymentMethod()).status(status).amount(request.getAmount()).build();
    }
    public PaymentDto mapToDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .paymentMethod(payment.getPayment_method())
                .amount(payment.getAmount())
                .bookingId(payment.getBookingId())
                .transaction_id(payment.getTransaction_id())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAtUTC())
                .updatedAt(payment.getUpdatedAtUTC())
                .build();
    }
}
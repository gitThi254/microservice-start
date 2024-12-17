package com.example.service;

import com.example.dto.PaymentDto;
import com.example.request.PaymentRequest;

import java.math.BigDecimal;
import java.util.List;

public interface IPaymentService {
    PaymentDto savePayment(PaymentRequest paymentRequest);
    PaymentDto getPaymentByOrderId(Long bookingId);
}

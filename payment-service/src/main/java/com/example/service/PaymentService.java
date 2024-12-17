package com.example.service;

import com.example.dto.PaymentDto;
import com.example.exception.NotFoundException;
import com.example.mapper.PaymentMapper;
import com.example.model.Payment;
import com.example.repository.PaymentRepository;
import com.example.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    @Override
    public PaymentDto savePayment(PaymentRequest paymentRequest) {
          Payment payment = paymentMapper.mapTo(paymentRequest);
          return  paymentMapper.mapToDto(paymentRepository.save(payment)) ;
    }

    @Override
    public PaymentDto getPaymentByOrderId(Long bookingId) {
        return paymentRepository.findByBookingId(bookingId).map(paymentMapper::mapToDto).orElseThrow(() -> new NotFoundException("Payment with orderId not found"));
    }
}

package com.example.controller;


import com.example.dto.PaymentDto;
import com.example.request.PaymentRequest;
import com.example.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/doPayment")
    public ResponseEntity<PaymentDto> doPayment(@RequestBody PaymentRequest paymentRequest) {
       return ResponseEntity.status(201).body(paymentService.savePayment(paymentRequest));
    }
    @GetMapping("/{bookingId}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Long bookingId) {
        return ResponseEntity.status(200).body(paymentService.getPaymentByOrderId(bookingId));
    }
}

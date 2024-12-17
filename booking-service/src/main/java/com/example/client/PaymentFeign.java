package com.example.client;

import com.example.response.PaymentDto;
import com.example.resquest.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//@FeignClient(name="payment-service", url="http://localhost:8086", path = "/payment")
@FeignClient(name="payment-service",path = "/payment")

public interface PaymentFeign {
    @PostMapping("/doPayment")
    PaymentDto doPayment(@RequestBody PaymentRequest paymentRequest);
    @GetMapping("/{bookingId}")
    PaymentDto getPayment(@PathVariable Long bookingId);
}

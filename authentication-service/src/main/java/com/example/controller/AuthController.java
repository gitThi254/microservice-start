package com.example.controller;

import com.example.dto.LoginReq;
import com.example.dto.UserDto;
import com.example.dto.UserReq;

import com.example.exception.BookingException;
import com.example.service.AuthService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
@Slf4j

public class AuthController {
   private final AuthService authService;
    @PostMapping(value = "/login")
    @CircuitBreaker(name = "external", fallbackMethod = "fallback")
    public ResponseEntity<?> login(@RequestBody LoginReq request) {
        return ResponseEntity.ok().body(authService.login(request));
    }
    @PostMapping(value = "/register")
    @CircuitBreaker(name = "external", fallbackMethod = "fallback")
        public ResponseEntity<?> register(@RequestBody UserReq request) {
          return ResponseEntity.ok().body(authService.register(request));
    }
    public ResponseEntity<?> fallback(Exception e) {
        log.error("Error user-service: " + e.getMessage());
        return ResponseEntity.status(503).body("Oops! Something went wrong on the user service, please try again later!");
    }
}

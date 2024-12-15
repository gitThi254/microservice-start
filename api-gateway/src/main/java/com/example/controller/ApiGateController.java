package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiGateController {
    @RequestMapping("/fallback/auth-service")
    public ResponseEntity<?> fallbackMethodAuthService(Exception ex){
        return   ResponseEntity.status(503).body( "Oops! Something went wrong on the auth service, please try again later!");
    }
    @RequestMapping("/fallback/user-service")
    public ResponseEntity<?> fallbackMethodUserService(Exception ex){
        return  ResponseEntity.status(503).body( "Oops! Something went wrong on the user service, please try again later!");
    }
}

package com.example.client;


import com.example.config.FeignConfig;
import com.example.dto.LoginDto;
import com.example.dto.LoginReq;
import com.example.dto.UserDto;
import com.example.dto.UserReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "user-service", path = "/api/v1/auth",  configuration = FeignConfig.class)
public interface AuthFeign {
    @PostMapping("/register")
    UserDto register(@RequestBody UserReq req);

    @PostMapping("/login")
    LoginDto login(@RequestBody LoginReq req);
}


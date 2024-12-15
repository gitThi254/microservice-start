package com.example.service;

import com.example.client.AuthFeign;
import com.example.dto.LoginDto;
import com.example.dto.LoginReq;
import com.example.dto.UserDto;
import com.example.dto.UserReq;
import com.example.exception.DuplicateKeyException;
import com.example.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthFeign authFeign;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginReq loginReq) {
            LoginDto userLoginDto = authFeign.login(loginReq);
            String accessToken = jwtUtil.generate(userLoginDto.getId(), userLoginDto.getRole() ,"ACCESS");
            String refreshToken = jwtUtil.generate(userLoginDto.getId(), userLoginDto.getRole(), "REFRESH");
            return new AuthResponse(accessToken, refreshToken);
    }

    public UserDto register(UserReq req) {
        return authFeign.register(req);
    }
}
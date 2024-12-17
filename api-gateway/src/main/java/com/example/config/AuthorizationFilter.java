package com.example.config;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthorizationFilter {
    public boolean admin(String path, String method) {
        Map<String, List<String>> apiMap = new HashMap<>();
        apiMap.put("GET", List.of("/api/admin", "/api/v1/user"));
        apiMap.put("POST", List.of("/rooms", "/api/v1/user"));
        apiMap.put("PUT", List.of("/rooms", "/api/v1/user"));
        apiMap.put("DELETE", List.of("/rooms", "/api/v1/user"));
        return apiMap.get(method).stream().anyMatch(path::startsWith);
    }
}

package com.example.controller;

import com.example.dto.user.UserDto;
import com.example.dto.user.UserReq;
import com.example.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserReq request) {
        userService.save(request);
        return ResponseEntity.status(201).body("Create user successfully");
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody UserReq request, @PathVariable String id) {
        userService.update(request, id);
        return ResponseEntity.status(200).body("Update user successfully");
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.status(201).body("Delete user successfully");
    }

}

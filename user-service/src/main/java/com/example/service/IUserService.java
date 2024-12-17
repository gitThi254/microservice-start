package com.example.service;

import com.example.dto.user.UserDto;
import com.example.dto.user.UserReq;

import java.util.List;

public interface IUserService {
    void save(UserReq userReq);
    void update(UserReq userReq, String id);
    UserDto getById(String id);
    List<UserDto> getAll();
    void delete(String id);

}

package com.example.service;

import com.example.dto.user.UserDto;
import com.example.dto.user.UserReq;
import com.example.exception.NotFoundException;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.repository.UserRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService  implements IUserService{
    private final UserRep userRep;
    private final UserMapper userMapper;

    @Override
    public void save(UserReq userReq) {
        User user = userMapper.mapTo(userReq);
        userMapper.mapToDto(userRep.save(user));
    }

    @Override
    public void update(UserReq userReq, String id) {
        User user = userRep.findById(id).orElseThrow(() -> new NotFoundException("User with id not found"));
        user.setEmail(userReq.getEmail());
        user.setPhone(userReq.getPhone());
        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        userRep.save(user);
    }

    @Override
    public UserDto getById(String id) {
        return userRep.findById(id).map(userMapper::mapToDto).orElseThrow(() -> new NotFoundException("User with id not found"));
    }

    @Override
    public List<UserDto> getAll() {
        return userRep.findAll().stream().map(userMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
         UserDto user = this.getById(id);
         userRep.deleteById(user.getId());
    }

}

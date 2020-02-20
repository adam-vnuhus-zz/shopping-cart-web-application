package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.model.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<UserDto> getListUser();

    public UserDto getUserById(int id);

    public List<UserDto> deleteUserById(int id);

    public UserDto register(User user);

    public UserDto updateUserById(User user, int id);
}

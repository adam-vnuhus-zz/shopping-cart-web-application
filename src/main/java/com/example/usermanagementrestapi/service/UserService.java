package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.model.dto.UserDto;
import com.example.usermanagementrestapi.model.request.CreateUserReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public List<UserDto> getListUser();

    public UserDto getUserById(int id);

    public UserDto createUser(CreateUserReq req);

    public List<UserDto> deleteUserById(int id);

    public UserDto updateUserById(User user, int id);
}

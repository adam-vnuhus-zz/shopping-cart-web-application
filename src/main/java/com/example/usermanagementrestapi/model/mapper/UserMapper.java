package com.example.usermanagementrestapi.model.mapper;

import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.model.dto.UserDto;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto temp = new UserDto();
        temp.setId(user.getId());
        temp.setName(user.getName());
        temp.setEmail(user.getEmail());
        temp.setPhone(user.getPhone());
        temp.setAvatar(user.getAvatar());

        return temp;
    }
}

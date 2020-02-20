package com.example.usermanagementrestapi.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private int id;

    private String name;

    private String email;

    private String phone;

    private String avatar;
}

package com.example.usermanagementrestapi.entity;

import lombok.*;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;

    private String name;

    private String email;

    private String phone;

    private String avatar;

    private String password;
}

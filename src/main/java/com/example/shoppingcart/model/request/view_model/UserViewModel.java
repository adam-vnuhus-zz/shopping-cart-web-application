package com.example.shoppingcart.model.request.view_model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserViewModel {

    private int id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;


    private String password;
}

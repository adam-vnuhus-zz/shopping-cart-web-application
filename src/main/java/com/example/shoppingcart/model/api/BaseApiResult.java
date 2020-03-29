package com.example.shoppingcart.model.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseApiResult {

    private boolean isSuccess;

    private String message;

    private Object data;

    private String link;
}

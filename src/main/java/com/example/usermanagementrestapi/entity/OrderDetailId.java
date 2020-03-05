package com.example.usermanagementrestapi.entity;

import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class OrderDetailId implements Serializable {
    private int orderId;

    private int productId;
}

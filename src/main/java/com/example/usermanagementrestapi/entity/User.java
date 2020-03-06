package com.example.usermanagementrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@NamedNativeQuery(
        name = "getUserInfo",
        query = "SELECT * FROM user WHERE id = ?1",
        resultClass = User.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @NotNull
    @Column(name="email", unique = true)
    private String email;

    @Column(name="phone", length = 11)
    private String phone;

    @Column(name="password")
    private String password;

    @Column(name="full_name")
    private String fullName;

//    @Column(name="avatar")
//    private String avatar;
//
//    @Column(name="birthday")
//    private Date birthday;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(255) default 'USER'")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}

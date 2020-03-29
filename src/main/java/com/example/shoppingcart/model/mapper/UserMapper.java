package com.example.shoppingcart.model.mapper;

import com.example.shoppingcart.entity.User;
import com.example.shoppingcart.model.dto.UserDto;
import com.example.shoppingcart.model.request.CreateUserReq;
import com.example.shoppingcart.model.request.UpdateUserReq;
import org.mindrot.jbcrypt.BCrypt;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto tmp = new UserDto();
        tmp.setId(user.getUserId());
        tmp.setName(user.getFullName());
        tmp.setPhone(user.getPhone());
//        tmp.setAvatar(user.getAvatar());
        tmp.setEmail(user.getEmail());
//        tmp.setBirthday(user.getBirthday());
        tmp.setAddress(user.getAddress());

        return tmp;
    }

    public static User toUser(CreateUserReq req) {
        User user = new User();
        user.setFullName(req.getName());
        user.setEmail(req.getEmail());

        user.setAddress(req.getAddress());
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        user.setRole("USER");

        return user;
    }

    public static User toUser(UpdateUserReq req, int id) {
        User user = new User();
        user.setUserId(id);
        user.setEmail(req.getEmail());
        user.setFullName(req.getName());
        user.setPhone(req.getPhone());
//        user.setAvatar(req.getAvatar());
        // Hash password using BCrypt
        String hash = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);

        return user;
    }
}

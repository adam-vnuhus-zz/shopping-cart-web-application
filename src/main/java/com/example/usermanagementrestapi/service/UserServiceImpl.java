package com.example.usermanagementrestapi.service;

import com.example.usermanagementrestapi.entity.User;
import com.example.usermanagementrestapi.exception.DuplicateRecordException;
import com.example.usermanagementrestapi.exception.NotFoundException;
import com.example.usermanagementrestapi.model.dto.UserDto;
import com.example.usermanagementrestapi.model.mapper.UserMapper;
import com.example.usermanagementrestapi.model.request.CreateUserReq;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private static ArrayList<User> users = new ArrayList<User>();

    static {
        users.add(new User(1,"Nguyen Van A","nva@gmail.com","123456789","avatar.img","123"));
        users.add(new User(2,"Nguyen Van B","nvb@gmail.com","123456789","avatar.img","123"));
        users.add(new User(3,"Nguyen Van C","nvc@gmail.com","123456789","avatar.img","123"));
        users.add(new User(4,"Nguyen Van D","nvd@gmail.com","123456789","avatar.img","123"));
    }

    @Override
    public List<UserDto> getListUser() {

        ArrayList<UserDto> userDtos = new ArrayList<UserDto>();

        //Convert users -> userDtos
        for (User user: users) {
            userDtos.add(UserMapper.toUserDto(user));
        }

        return userDtos;
    }

    @Override
    public UserDto getUserById(int id) {
        for (User user: users) {
            if(user.getId() == id) {
                return UserMapper.toUserDto(user);
            }
        }
        throw new NotFoundException("Không tìm thấy user");
    }

    @Override
    public UserDto createUser(CreateUserReq req) {
        //Validate
        //Kiem tra email da ton tai hay chua
        for (User user: users) {
            if(user.getEmail().equals(req.getEmail())) {
                throw new DuplicateRecordException("Email da ton tai trong he thong");
            }
        }

        //Convert CreateUserReq
        User user = new User();
        user.setId(users.size());
        user.setPhone(req.getPhone());
        user.setName(req.getFullName());
        user.setEmail(req.getEmail());
        //Ma hoa mat khau
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt(12)));

        users.add(user);

        return null;
    }

    @Override
    public UserDto updateUserById(User user, int id) {
        User temp = user;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, temp);
                break;
            }
        }
        return UserMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> deleteUserById(int id) {
        ArrayList<UserDto> userDtos = new ArrayList<UserDto>();
        for (User user: users) {
            if(user.getId() == id) {
                users.remove(user);
                break;
            }
        }
        for (User user: users) {
            userDtos.add(UserMapper.toUserDto(user));
        }
        return userDtos;
    }
}

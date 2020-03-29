package com.example.shoppingcart.service.impl;

import com.example.shoppingcart.entity.ShoppingCart;
import com.example.shoppingcart.entity.User;
import com.example.shoppingcart.exception.DuplicateRecordException;
import com.example.shoppingcart.exception.InternalServerException;
import com.example.shoppingcart.exception.NotFoundException;
import com.example.shoppingcart.model.dto.UserDto;
import com.example.shoppingcart.model.mapper.UserMapper;
import com.example.shoppingcart.model.request.CreateUserReq;
import com.example.shoppingcart.model.request.UpdateUserReq;
import com.example.shoppingcart.repository.UserRepository;
import com.example.shoppingcart.service.ShoppingCartService;
import com.example.shoppingcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ShoppingCartService shoppingCartService;

    @Override
    public UserDto createUser(CreateUserReq req) {
        // Check email exist
        User user = userRepository.findByEmail(req.getEmail());
        if (user != null) {
            throw new DuplicateRecordException("Email is already in use");
        }

        user = UserMapper.toUser(req);
        userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    @Override
    public void checkUser(HttpServletResponse response, HttpServletRequest request, Principal principal) {

        Cookie[] cookie = request.getCookies();

        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            ShoppingCart cartEntity = shoppingCartService.findByUserName(username);
            if (cartEntity != null) {
                Cookie cookie1 = new Cookie("user", cartEntity.getUsername());
                cookie1.setPath("/");
                response.addCookie(cookie1);
            } else {
                ShoppingCart cart = new ShoppingCart();
                cart.setUsername(username);
                shoppingCartService.createShoppingCart(cart);

                Cookie cookie2 = new Cookie("user", username);
                cookie2.setPath("/");
                response.addCookie(cookie2);
            }
        }
    }

    @Override
    public String getUser(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();

        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("username")) return c.getValue();
            }
        }

        return null;

    }

    @Override
    public List<UserDto> getListUser() {
        List<User> users = userRepository.findAll();

        List<UserDto> rs = new ArrayList<>();
        for (User user : users) {
            rs.add(UserMapper.toUserDto(user));
        }

        return rs;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }

        return user.get();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        return null;
    }

    @Override
    public Boolean updateUser(User user) {

        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public UserDto removeUser(int id) {
        return null;
    }

    @Override
    public User findByUseremail(String email) {

        return StreamSupport
                .stream(userRepository.findByUseremail(email).spliterator(), false)
                .findFirst().orElse(null);
    }

    @Override
    public Page<User> findUserLikeName(String name, int page) {
        Page<User> rs = userRepository.findUserByName(name, PageRequest.of(page, 10, Sort.by("id").descending()));
        return rs;
    }

    @Override
    public UserDto updateUser(UpdateUserReq req, int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }

        User updateUser = UserMapper.toUser(req, id);
        try {
            userRepository.save(updateUser);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't update user");
        }

        return UserMapper.toUserDto(updateUser);
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerException("Database error. Can't delete user");
        }
    }
}
